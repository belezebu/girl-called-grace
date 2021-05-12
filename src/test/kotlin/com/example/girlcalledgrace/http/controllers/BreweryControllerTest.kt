package com.example.girlcalledgrace.http.controllers

import com.example.girlcalledgrace.clients.openbrewery.Brewery
import com.example.girlcalledgrace.clients.openbrewery.BreweryParams
import com.example.girlcalledgrace.clients.openbrewery.OpenBreweryClient
import com.example.girlcalledgrace.exceptions.EntityNotFoundException
import com.example.girlcalledgrace.http.entities.BreweryRepresentation
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [BreweryController::class])
@Import(OpenBreweryClient::class)
internal class BreweryControllerTest {

    @Autowired
    private lateinit var client: WebTestClient

    @MockkBean
    private lateinit var openBreweryClient: OpenBreweryClient

    @Test
    fun searchBreweriesTest() {
        coEvery {
            openBreweryClient.searchBreweries(searchQuery)
        } returns breweries
        client.mutateWith(mockJwt())
                .get()
                .uri { it.path("/breweries").queryParam("text", searchQuery).build() }
                .exchange()
                .expectStatus()
                .isOk
                .expectBodyList<BreweryRepresentation>()
    }

    @Test
    fun listBreweriesTest() {
        coEvery {
            openBreweryClient.listBreweries(BreweryParams())
        } returns breweries
        client.mutateWith(mockJwt())
                .get()
                .uri { it.path("/breweries").build() }
                .exchange()
                .expectStatus()
                .isOk
                .expectBodyList<BreweryRepresentation>()


    }

    @Test
    fun getBreweryTest() {
        coEvery {
            openBreweryClient.getBrewery(brewery.id)
        } returns brewery
        client.mutateWith(mockJwt())
                .get()
                .uri { it.path("/breweries").path("/{breweryId}").build(brewery.id) }
                .exchange()
                .expectStatus()
                .isOk
                .expectBody(BreweryRepresentation::class.java)
    }

    @Test
    fun getBreweryNotFoundTest() {
        coEvery {
            openBreweryClient.getBrewery(brewery.id)
        } throws EntityNotFoundException("Brewery ${brewery.id} Not Found")
        client.mutateWith(mockJwt())
                .get()
                .uri { it.path("/breweries").path("/{breweryId}").build(brewery.id) }
                .exchange()
                .expectStatus()
                .isNotFound
    }

    companion object {
        private const val searchQuery = "dog"
        private val brewery = Brewery(123, "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "b", "c", "d", "e", "f", "g")
        private val breweries = listOf(brewery)
    }
}

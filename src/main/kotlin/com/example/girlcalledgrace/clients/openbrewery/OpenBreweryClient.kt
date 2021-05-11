package com.example.girlcalledgrace.clients.openbrewery

import com.example.girlcalledgrace.exceptions.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class OpenBreweryClient(private val webClient: WebClient) {
    suspend fun searchBreweries(query: String) = webClient.get()
            .uri { it.path("/breweries/search").queryParam("query", query).build() }
            .retrieve()
            .awaitBody<List<Brewery>>()

    suspend fun listBreweries() = webClient.get()
            .uri { it.path("/breweries").build() }
            .retrieve()
            .awaitBody<List<Brewery>>()

    suspend fun getBrewery(breweryId: String) = webClient.get()
            .uri { it.path("/breweries").path("/{breweryId}").build(breweryId) }
            .retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals) { throw EntityNotFoundException("Brewery $breweryId Not Found") }
            .awaitBody<Brewery>()
}
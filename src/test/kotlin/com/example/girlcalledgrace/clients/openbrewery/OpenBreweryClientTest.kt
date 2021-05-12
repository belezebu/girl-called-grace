package com.example.girlcalledgrace.clients.openbrewery

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono
import java.net.URI
import java.util.function.Function
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
internal class OpenBreweryClientTest {

    private val webClient = mockk<WebClient>()
    private val victim = OpenBreweryClient(webClient)

    @Test
    fun searchBreweriesTest() {
        runBlocking {
            every {
                webClient.get()
                        .uri(any<Function<UriBuilder, URI>>())
                        .retrieve()
                        .bodyToMono(any<ParameterizedTypeReference<List<Brewery>>>())
            } returns Mono.just(listOf(brewery))
            val result = victim.searchBreweries(searchQuery)
            assertEquals(listOf(brewery), result)
        }
    }

    @Test
    fun listBreweriesTest() {
        runBlocking {
            every {
                webClient.get()
                        .uri(any<Function<UriBuilder, URI>>())
                        .retrieve()
                        .bodyToMono(any<ParameterizedTypeReference<List<Brewery>>>())
            } returns Mono.just(listOf(brewery))
            val result = victim.listBreweries(mapOf())
            assertEquals(listOf(brewery), result)
        }
    }

    @Test
    fun getBreweryTest() {
        runBlocking {
            every {
                webClient.get()
                        .uri(any<Function<UriBuilder, URI>>())
                        .retrieve()
                        .onStatus(any(), any())
                        .bodyToMono(any<ParameterizedTypeReference<Brewery>>())
            } returns Mono.just(brewery)
            val result = victim.getBrewery("12345")
            assertEquals(brewery, result)
        }
    }

    companion object {
        private const val searchQuery = "dog"
        private val brewery = Brewery("a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "b", "c", "d", "e", "f", "g")
    }
}
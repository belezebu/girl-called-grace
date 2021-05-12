package com.example.girlcalledgrace.clients.openbrewery

import com.example.girlcalledgrace.exceptions.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class OpenBreweryClient(private val webClient: WebClient) {
    suspend fun searchBreweries(query: String): List<Brewery> = webClient.get()
            .uri { it.path("/breweries/search").queryParam("query", query).build() }
            .retrieve()
            .awaitBody()

    suspend fun listBreweries(allParams: Map<String, String>): List<Brewery> = webClient.get()
            .uri { it.path("/breweries").also { uri -> allParams.forEach { params -> uri.queryParam(params.key, params.value) } }.build() }
            .retrieve()
            .awaitBody()

    suspend fun getBrewery(breweryId: String): Brewery = webClient.get()
            .uri { it.path("/breweries").path("/{breweryId}").build(breweryId) }
            .retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals) { throw EntityNotFoundException("Brewery $breweryId Not Found") }
            .awaitBody()
}
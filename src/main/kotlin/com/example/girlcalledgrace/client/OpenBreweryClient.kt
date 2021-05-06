package com.example.girlcalledgrace.client

import com.example.girlcalledgrace.model.Brewery
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class OpenBreweryClient(private val webClient: WebClient) {
    suspend fun searchBreweries(query: String) = webClient.get()
            .uri { it.path("/breweries/search").queryParam("query", query).build() }
            .retrieve()
            .awaitBody<List<Brewery>>()

    suspend fun findBrewery(breweryId: String) = webClient.get()
            .uri { it.path("/breweries").path("/{breweryId}").build(breweryId) }
            .retrieve()
            .awaitBody<Brewery>()

}
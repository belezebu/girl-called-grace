package com.example.girlcalledgrace.controller

import com.example.girlcalledgrace.client.OpenBreweryClient
import com.example.girlcalledgrace.model.Brewery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BreweryController(private val openBreweryClient: OpenBreweryClient) {

    @GetMapping("/breweries/search")
    suspend fun searchBreweries(@RequestParam(name = "query") searchQuery: String): List<Brewery> = openBreweryClient.searchBreweries(searchQuery)

    @GetMapping("/breweries/{id}")
    suspend fun findBrewery(@PathVariable(name = "id") breweryId: String): Brewery = openBreweryClient.findBrewery(breweryId)
}
package com.example.girlcalledgrace.controllers

import com.example.girlcalledgrace.clients.openbrewery.OpenBreweryClient
import com.example.girlcalledgrace.clients.openbrewery.Brewery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BreweryController(private val openBreweryClient: OpenBreweryClient) {

    @GetMapping("/breweries")
    suspend fun searchBreweries(@RequestParam(name = "text") searchText: String): List<Brewery> = openBreweryClient.searchBreweries(searchText)

    @GetMapping("/breweries/{id}")
    suspend fun findBrewery(@PathVariable(name = "id") breweryId: String): Brewery = openBreweryClient.findBrewery(breweryId)
}
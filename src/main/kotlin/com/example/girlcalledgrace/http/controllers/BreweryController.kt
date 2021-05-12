package com.example.girlcalledgrace.http.controllers

import com.example.girlcalledgrace.clients.openbrewery.OpenBreweryClient
import com.example.girlcalledgrace.exceptions.EntityNotFoundException
import com.example.girlcalledgrace.http.entities.BreweryRepresentation
import com.example.girlcalledgrace.http.entities.adapt
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BreweryController(private val openBreweryClient: OpenBreweryClient) {

    @GetMapping("/breweries")
    suspend fun getBreweries(@RequestParam allParams: Map<String, String>): List<BreweryRepresentation> {
        val searchText = allParams["text"]
        if (searchText != null) {
            return openBreweryClient.searchBreweries(searchText).map { it.adapt() }
        }
        return openBreweryClient.listBreweries(allParams).map { it.adapt() }
    }

    @GetMapping("/breweries/{id}")
    suspend fun getBrewery(@PathVariable(name = "id") breweryId: String): BreweryRepresentation = openBreweryClient.getBrewery(breweryId).adapt()

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun exceptionHandler(): ResponseEntity<Unit> = ResponseEntity.notFound().build()
}
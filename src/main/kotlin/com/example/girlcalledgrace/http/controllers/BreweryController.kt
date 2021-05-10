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
    suspend fun getBreweries(@RequestParam(name = "text") searchText: String?): List<BreweryRepresentation> {
        if (searchText != null) {
            return openBreweryClient.searchBreweries(searchText).map { it.adapt() }
        }
        return openBreweryClient.listBreweries().map { it.adapt() }
    }

    @GetMapping("/breweries/{id}")
    suspend fun findBrewery(@PathVariable(name = "id") breweryId: String): BreweryRepresentation = openBreweryClient.findBrewery(breweryId).adapt()

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun exceptionHandler(): ResponseEntity<Unit> = ResponseEntity.notFound().build()
}
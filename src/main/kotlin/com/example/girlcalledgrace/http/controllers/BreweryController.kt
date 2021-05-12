package com.example.girlcalledgrace.http.controllers

import com.example.girlcalledgrace.clients.openbrewery.BreweryParams
import com.example.girlcalledgrace.clients.openbrewery.OpenBreweryClient
import com.example.girlcalledgrace.http.entities.BreweryRepresentation
import com.example.girlcalledgrace.http.entities.adapt
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Brewery", description = "Returns information about breweries")
class BreweryController(private val openBreweryClient: OpenBreweryClient) {

    @GetMapping("/breweries")
    @Operation(summary = "Get and Search Breweries")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "List and Search Breweries", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = BreweryRepresentation::class)))))])
    ]
    )
    suspend fun getBreweries(
            @RequestParam("text") searchText: String?,
            @RequestParam("city") city: String?,
            @RequestParam("distance") distance: String?,
            @RequestParam("name") name: String?,
            @RequestParam("state") state: String?,
            @RequestParam("postal") postal: String?,
            @RequestParam("type") type: String?,
            @RequestParam("page") page: Int?,
            @RequestParam("size") size: Int?,
            @RequestParam("sort") sort: String?,
    ): List<BreweryRepresentation> {
        if (searchText != null) {
            return openBreweryClient.searchBreweries(searchText).map { it.adapt() }
        }
        return openBreweryClient.listBreweries(BreweryParams(city, distance, name, state, postal, type, page, size, sort)).map { it.adapt() }
    }

    @Operation(summary = "Get Brewery")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Get Single Brewery", content = [
            (Content(mediaType = "application/json", schema = (
                    Schema(implementation = BreweryRepresentation::class))))]),
        ApiResponse(responseCode = "404", description = "Brewery Not Found", content = [Content()])]
    )
    @GetMapping("/breweries/{id}")
    suspend fun getBrewery(@PathVariable(name = "id") breweryId: Int): BreweryRepresentation = openBreweryClient.getBrewery(breweryId).adapt()


}
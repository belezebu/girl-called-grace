package com.example.girlcalledgrace.http.entities

import com.example.girlcalledgrace.clients.openbrewery.Brewery

data class BreweryRepresentation(
        val id: Int,
        val name: String,
        val breweryType: String,
        val street: String?,
        val address2: String?,
        val address3: String?,
        val city: String,
        val countyProvidence: String?,
        val state: String,
        val postalCode: String,
        val country: String,
        val longitude: String?,
        val latitude: String?,
        val phone: String?,
        val websiteUrl: String?,
        val updatedAt: String,
        val createdAt: String,
)

fun Brewery.adapt() = BreweryRepresentation(id, name, breweryType, street, address2, address3, city, countyProvidence, state, postalCode, country, longitude, latitude, phone, websiteUrl, updatedAt, createdAt)
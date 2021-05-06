package com.example.girlcalledgrace.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Brewery(@JsonProperty("id") val id: String,
                   @JsonProperty("name") val name: String,
                   @JsonProperty("brewery_type") val breweryType: String,
                   @JsonProperty("street") val street: String?,
                   @JsonProperty("address_2") val address2: String?,
                   @JsonProperty("address_3") val address3: String?,
                   @JsonProperty("city") val city: String,
                   @JsonProperty("county_province") val countyProvidence: String?,
                   @JsonProperty("state") val state: String,
                   @JsonProperty("postal_code") val postalCode: String,
                   @JsonProperty("country") val country: String,
                   @JsonProperty("longitude") val longitude: String?,
                   @JsonProperty("latitude") val latitude: String?,
                   @JsonProperty("phone") val phone: String?,
                   @JsonProperty("website_url") val websiteUrl: String?,
                   @JsonProperty("updated_at") val updatedAt: String,
                   @JsonProperty("created_at") val createdAt: String)
package com.example.girlcalledgrace.clients.openbrewery

data class BreweryParams(
        val city: String? = null,
        val distance: String? = null,
        val name: String? = null,
        val state: String? = null,
        val postal: String? = null,
        val type: String? = null,
        val page: Int? = null,
        val size: Int? = null,
        val sort: String? = null,
)

fun BreweryParams.toOpenBreweryParams() = mapOf(
        "by_city" to city,
        "by_dist" to distance,
        "by_name" to name,
        "by_state" to state,
        "by_postal" to postal,
        "by_type" to type,
        "page" to page?.toString(),
        "per_page" to size?.toString(),
        "sort" to sort
).filterValues { it != null }
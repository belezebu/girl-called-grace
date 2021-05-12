package com.example.girlcalledgrace.clients.openbrewery

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BreweryParamsTest {

    @Test
    fun toBreweryParamsTest() {
        //empty params
        val result = BreweryParams().toOpenBreweryParams()
        assertEquals(result, mapOf())
        //one parameter
        val cityParam = BreweryParams("Porto")
        val cityResult = cityParam.toOpenBreweryParams()
        assertEquals(cityResult, mapOf("by_city" to cityParam.city))
        //all parameters
        val allBreweryParams = BreweryParams("san_diego", "38.8977,77.0365", "cooper", "ohio", "44107", "micro", 15, 25, "type,-name")
        val allBreweryParamsResult = allBreweryParams.toOpenBreweryParams()
        assertEquals(allBreweryParamsResult, mapOf("by_city" to allBreweryParams.city,
                "by_dist" to allBreweryParams.distance,
                "by_name" to allBreweryParams.name,
                "by_state" to allBreweryParams.state,
                "by_postal" to allBreweryParams.postal,
                "by_type" to allBreweryParams.type,
                "page" to allBreweryParams.page?.toString(),
                "per_page" to allBreweryParams.size?.toString(),
                "sort" to allBreweryParams.sort))
    }
}
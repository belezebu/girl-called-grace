package com.example.girlcalledgrace.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties("clients.open-brewery")
@Component
data class OpenBreweryProperties(
        var baseUrl: String = "https://api.openbrewerydb.org",
)
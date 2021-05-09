package com.example.girlcalledgrace.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class OpenBreweryConfig {
    @Bean
    fun webClient(builder: WebClient.Builder): WebClient = builder
            .baseUrl("https://api.openbrewerydb.org")
            .build()
}

package com.example.girlcalledgrace.config

import io.swagger.v3.oas.models.Components

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfig {
    @Bean
    fun girlCalledGraceApi(): OpenAPI {
        val securitySchemeName = "bearerAuth"
        return OpenAPI().info(Info().title("Girl Called Grace")
                .description("This document describes the endpoints to get brewery information")
                .version("v1.0.0"))
                .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
                .components(Components().addSecuritySchemes(securitySchemeName,
                        SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
    }
}
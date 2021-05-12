package com.example.girlcalledgrace.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class SecurityConfig {

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    lateinit var issuerUri: String

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http
            .authorizeExchange()
            .pathMatchers("/documentation/**").permitAll()
            .pathMatchers("/breweries/**").hasAuthority("SCOPE_breweries:read")
            .anyExchange().authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt().and().and().build()

    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder = ReactiveJwtDecoders.fromOidcIssuerLocation(issuerUri)
}
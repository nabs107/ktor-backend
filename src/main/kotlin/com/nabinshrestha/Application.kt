package com.nabinshrestha

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import com.nabinshrestha.plugins.configureRouting
import com.nabinshrestha.routes.*
import com.zaxxer.hikari.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.*
import org.jetbrains.exposed.sql.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        initDB()
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }

        val secret = "secret"
        val issuer = "http://0.0.0.0:8080/"
        val audience = "http://0.0.0.0:8080/hello"
        val myRealm = "Access to 'hello'"

        install(Authentication) {
            jwt("auth-jwt") {
                realm = myRealm
                verifier(JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
                )
                validate { jwtCredential ->
                    if (jwtCredential.payload.getClaim("username").asString() != "") {
                        JWTPrincipal(jwtCredential.payload)
                    } else {
                        null
                    }
                }
            }
        }
        configureRouting()
        registerLoginRoutes()
        registerRegisterRoutes()
        registerCustomerRoutes()
        registerActorRoutes()
        registerAddressRoutes()
        registerCategoryRoutes()
        registerFilmActorRoutes()
        registerFilmRoutes()
    }.start(wait = true)
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    val ds = HikariDataSource(config)
    Database.connect(ds)
}

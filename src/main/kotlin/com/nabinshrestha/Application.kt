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
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.netty.handler.codec.http.HttpHeaderDateFormat.*
import kotlinx.serialization.json.*
import org.jetbrains.exposed.sql.*
import sun.security.pkcs11.wrapper.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    embeddedServer(Netty, port = 8080) {

        initDB()

        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }

//        val secret = "secret"
//        val issuer = "http://0.0.0.0:8080/"
//        val audience = "http://0.0.0.0:8080/hello"
//        val myRealm = "Access to 'hello'"
//
//        install(Authentication) {
//            jwt("auth-jwt") {
//                realm = myRealm
//                verifier(JWT
//                    .require(Algorithm.HMAC256(secret))
//                    .withAudience(audience)
//                    .withIssuer(issuer)
//                    .build()
//                )
//                validate { jwtCredential ->
//                    if (jwtCredential.payload.getClaim("username").asString() != "") {
//                        JWTPrincipal(jwtCredential.payload)
//                    } else {
//                        null
//                    }
//                }
//            }
//        }

        get("/") {
            call.respondText("Hello, world!")
        }

//        configureRouting()
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

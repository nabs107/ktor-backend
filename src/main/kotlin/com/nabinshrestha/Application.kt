package com.nabinshrestha

import com.nabinshrestha.plugins.configureRouting
import com.nabinshrestha.routes.*
import com.zaxxer.hikari.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import org.jetbrains.exposed.sql.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        initDB()
        install(ContentNegotiation) {
            json()
        }
        configureRouting()
        registerCustomerRoutes()
        registerActorRoutes()
        registerAddressRoutes()
        registerCategoryRoutes()
        registerFilmActorRoutes()
    }.start(wait = true)
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    val ds = HikariDataSource(config)
    Database.connect(ds)
}

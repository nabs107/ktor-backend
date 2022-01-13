package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.connectWithUsRoutes() {
    route("connectWithUs") {
        get {
            val connectWithUsList: ArrayList<Menu> = arrayListOf()
            transaction {
                ConnectWithUs.selectAll().map {
                    connectWithUsList.add(
                        Menu(
                            id = it[ConnectWithUs.id],
                            name = it[ConnectWithUs.name],
                            code = it[ConnectWithUs.code],
                            iconURLString = it[ConnectWithUs.iconURLString],
                            routeURLString = it[ConnectWithUs.routeURLString]
                        )
                    )
                }
            }
            call.respond(
                HttpStatusCode.OK,
                MenuAPI(success = true, message = "Connect With Us List Obtained", connectWithUsList)
            )
        }
    }
}

fun Application.registerConnectWithUsRoutes() {
    routing {
        connectWithUsRoutes()
    }
}
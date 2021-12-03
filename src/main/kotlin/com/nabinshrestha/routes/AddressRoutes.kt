package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.addressRoutes() {
    route("/address") {

        get {
            val addressList: ArrayList<Address> = arrayListOf()
            transaction {
                Addresses.selectAll().map {
                    addressList.add(
                        Address(
                            id = it[Addresses.id],
                            address = it[Addresses.address],
                            address2 = it[Addresses.address2],
                            district = it[Addresses.district],
                            cityId = it[Addresses.cityId],
                            postalCode = it[Addresses.postalCode],
                            phone = it[Addresses.phone],
                            lastUpdate = it[Addresses.lastUpdate].toString()
                        )
                    )
                }
            }
            call.respond(addressList)
        }
    }
}

fun Application.registerAddressRoutes() {
    routing {
        addressRoutes()
    }
}
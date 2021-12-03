package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.customerRouting() {
    route("/customers") {
        get {
            val customerList: ArrayList<Customer> = arrayListOf()
            transaction {
                Customers.selectAll().map {
                    customerList.add(
                        Customer(
                            customerId = it[Customers.customerId],
                            storeId = it[Customers.storeId],
                            firstName = it[Customers.firstName],
                            lastName = it[Customers.lastName],
                            email = it[Customers.email],
                            addressId = it[Customers.addressId],
                            isActive = it[Customers.isActive],
                            createDate = it[Customers.createDate].toString(),
                            lastUpdate = it[Customers.lastUpdate].toString(),
                            active = it[Customers.active]
                        )
                    )
                }
            }
            call.respond(customerList)
        }
        get("{id}") {

        }
        post {
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id}") {

        }
    }
}

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}
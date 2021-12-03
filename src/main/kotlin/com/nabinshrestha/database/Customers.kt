package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object Customers : Table("customer") {
    val customerId = integer("customer_id")
    val storeId = integer("store_id")
    val firstName = text("first_name")
    val lastName = text("last_name")
    val email = text("email")
    val addressId = integer("address_id")
    val isActive = bool("activebool")
    val createDate = date("create_date")
    val lastUpdate = date("last_update")
    val active = integer("active")
}
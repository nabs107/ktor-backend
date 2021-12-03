package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object Addresses : Table(name = "address") {
    val id = integer("address_id")
    val address = text("address")
    val address2 = text("address2")
    val district = text("district")
    val cityId = integer("city_id")
    val postalCode = text("postal_code")
    val phone = text("phone")
    val lastUpdate = date("last_update")
}
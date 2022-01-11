package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object Promotions : Table(name = "promotions") {
    val productId = integer("product_id")
    val productName = text("product_name")
    val productDescription = text("product_description")
    val productURL = text("product_url")
}
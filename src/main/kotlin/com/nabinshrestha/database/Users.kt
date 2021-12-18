package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object Users : Table(name = "users") {
    val id = integer("id")
    val email = text(name = "email")
    val password = text("password")
}
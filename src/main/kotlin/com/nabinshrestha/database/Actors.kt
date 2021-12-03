package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object Actors : Table(name = "actor") {
    val id = integer("actor_id")
    val firstName = text("first_name")
    val lastName = text("last_name")
    val lastUpdate = date("last_update")
}
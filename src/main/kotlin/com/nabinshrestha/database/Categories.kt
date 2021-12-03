package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object Categories : Table("category") {
    val id = integer("category_id")
    val name = text("name")
    val lastUpdate = date("last_update")
}
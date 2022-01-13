package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object ConnectWithUs : Table(name = "connect_with_us") {
    val id = integer("id")
    val name = text("name")
    val code = text("code")
    val iconURLString = text("icon_url")
    val routeURLString = text("route_url")
}
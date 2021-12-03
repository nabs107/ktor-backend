package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.categoryRoutes() {
    route("categories") {

        get {
            val categoryList: ArrayList<Category> = arrayListOf()
            transaction {
                Categories.selectAll().map {
                    categoryList.add(
                        Category(
                            id = it[Categories.id],
                            name = it[Categories.name],
                            lastUpdate = it[Categories.lastUpdate].toString()
                        )
                    )
                }
            }
            call.respond(categoryList)
        }
    }
}

fun Application.registerCategoryRoutes() {
    routing {
        categoryRoutes()
    }
}
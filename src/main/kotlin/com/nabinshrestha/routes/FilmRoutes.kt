package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.filmRouting() {
    route("/films") {

        get {
            val filmList: ArrayList<Film> = arrayListOf()
            transaction {
                Films.selectAll().map {
                    filmList.add(
                        Film(
                            filmId = it[Films.filmId],
                            title = it[Films.title],
                            description = it[Films.description],
                            releaseYear = it[Films.releaseYear],
                            languageId = it[Films.languageId],
                            rentalDuration = it[Films.rentalDuration],
                            rentalRate = it[Films.rentalRate],
                            length = it[Films.length],
                            replacementCost = it[Films.replacementCost],
                            rating = it[Films.rating]
//                            specialFeatures = it[Films.specialFeatures].toString()
                        )
                    )
                }
            }
            call.respond(filmList)
        }
    }
}

fun Application.registerFilmRoutes() {
    routing {
        filmRouting()
    }
}
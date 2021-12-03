package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.filmActorRouting() {
    route("/filmActors") {

        get {
            val filmActorList: ArrayList<FilmActor> = arrayListOf()
            transaction {
                FilmActors.selectAll().map {
                    filmActorList.add(
                        FilmActor(
                            actorId = it[FilmActors.actorId],
                            filmId = it[FilmActors.filmId]
                        )
                    )
                }
            }
            call.respond(filmActorList)
        }
    }
}

fun Application.registerFilmActorRoutes() {
    routing {
        filmActorRouting()
    }
}
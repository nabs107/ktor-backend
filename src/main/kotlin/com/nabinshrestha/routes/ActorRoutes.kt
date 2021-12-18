package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.dtos.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.exceptions.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import kotlin.collections.ArrayList

fun Route.actorRoutes() {
    route("/actors") {

        get {
            val actors: ArrayList<Actor> = arrayListOf()
            transaction {
                Actors.selectAll().map {
                    actors.add(
                        Actor(
                            id = it[Actors.id],
                            firstName = it[Actors.firstName],
                            lastName = it[Actors.lastName],
                            lastUpdate = it[Actors.lastUpdate].toString()
                        )
                    )
                }
            }
            actors.sortBy { it.id }
            call.respond(actors)
        }

        post {
            val actor: ActorDTO = call.receive()
            actor.firstName?.let { firstName ->
                actor.lastName?.let { lastName ->
                    transaction {
                        Actors.insert {
                            it[Actors.firstName] = firstName
                            it[Actors.lastName] = lastName
                        }
                    }
                } ?: run {
                    call.respond(status = HttpStatusCode.BadRequest, APIModel(success = false, message = "Last Name is required!"))
                    return@post
                }
            } ?: run {
                call.respond(status = HttpStatusCode.BadRequest, APIModel(success = false, message = "First Name is required!"))
                return@post
            }
            call.respond(status = HttpStatusCode.Created, APIModel(success = true, message = "Actor added successfully."))
        }

        put("/{id}") {
            val actor: ActorDTO = call.receive()
            val id = call.parameters["id"]?.toIntOrNull() ?: -1

            if (id == -1) {
                call.respond(status = HttpStatusCode.BadRequest, APIModel(success = false, message = "Passed id is not valid!"))
                return@put
            }

            transaction {
                actor.firstName?.let { firstName ->
                    actor.lastName?.let { lastName ->
                        Actors.update({ Actors.id eq id }) {
                            it[Actors.firstName] = firstName
                            it[Actors.lastName] = lastName
                        }
                    } ?: run {
                        Actors.update( {Actors.id eq id} ) {
                            it[Actors.firstName] = firstName
                        }
                    }
                }

            }
            call.respond(status = HttpStatusCode.OK, APIModel(success = true, message = "Actor modified successfully."))
        }

        delete("/{id}") {

            val id = call.parameters["id"]?.toIntOrNull()

            id?.let {
                var actorExists: Boolean = false
                transaction {
                    actorExists = Actors.select(exists(Actors.select { Actors.id eq id } )).count() > 0
                    if (actorExists) {
                        FilmActors.deleteWhere { FilmActors.actorId eq id }
                        Actors.deleteWhere { Actors.id eq id }
                    }
                }
                if (actorExists) {
                    call.respond(status = HttpStatusCode.OK, APIModel(success = true, message = "Deleted Successfully."))
                } else {
                    call.respond(status = HttpStatusCode.OK, APIModel(success = true, message = "Provided id doesn't exist."))
                }
            } ?: run {
                call.respond(status = HttpStatusCode.BadRequest, APIModel(success = false, message = "Provide a valid id."))
            }
        }
    }
}

fun Application.registerActorRoutes() {
    routing {
        authenticate("auth-jwt") {
            actorRoutes()
        }
    }
}
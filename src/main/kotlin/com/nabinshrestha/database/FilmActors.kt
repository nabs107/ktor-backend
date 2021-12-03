package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object FilmActors : Table("film_actor") {
    val actorId = integer("actor_id")
    val filmId = integer("film_id")
}
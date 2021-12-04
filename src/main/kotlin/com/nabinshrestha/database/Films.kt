package com.nabinshrestha.database

import org.jetbrains.exposed.sql.*

object Films : Table("film") {
    val filmId = integer("film_id")
    val title = text("title")
    val description = text("description")
    val releaseYear = integer("release_year")
    val languageId = integer("language_id")
    val rentalDuration = integer("rental_duration")
    val rentalRate = double("rental_rate")
    val length = integer("length")
    val replacementCost = double("replacement_cost")
    val rating = text("rating")
//    val specialFeatures = text("special_features")
//    val actorName = (text("first_name") references Actors.firstName)
}
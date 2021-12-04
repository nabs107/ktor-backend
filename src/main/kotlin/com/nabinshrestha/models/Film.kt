package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val filmId: Int,
    val title: String,
    val description: String,
    val releaseYear: Int,
    val languageId: Int,
    val rentalDuration: Int,
    val rentalRate: Double,
    val length: Int,
    val replacementCost: Double,
    val rating: String
//    val specialFeatures: String
)

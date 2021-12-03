package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
data class FilmActor(
    val actorId: Int,
    val filmId: Int
)

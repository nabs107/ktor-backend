package com.nabinshrestha.models

import kotlinx.serialization.*

@Serializable
data class Actor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val lastUpdate: String
)
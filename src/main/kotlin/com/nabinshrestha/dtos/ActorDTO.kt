package com.nabinshrestha.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ActorDTO(
    val firstName: String? = null,
    val lastName: String? = null
)
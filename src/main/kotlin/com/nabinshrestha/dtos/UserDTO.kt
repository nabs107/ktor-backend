package com.nabinshrestha.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val email: String,
    val password: String
)

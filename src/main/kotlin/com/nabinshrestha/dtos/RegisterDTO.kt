package com.nabinshrestha.dtos

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDTO(
    val email: String? = null,
    val password: String? = null
)

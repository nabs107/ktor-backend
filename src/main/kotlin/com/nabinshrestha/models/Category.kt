package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val lastUpdate: String
)

package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
data class Promotion(
    val productId: Int,
    val productName: String,
    val productDescription: String,
    val productURL: String
)
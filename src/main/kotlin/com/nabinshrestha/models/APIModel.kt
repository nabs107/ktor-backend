package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
open class APIModel(
    val success: Boolean,
    var message: String
)
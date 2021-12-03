package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val id: Int,
    val address: String? = null,
    val address2: String? = null,
    val district: String,
    val cityId: Int,
    val postalCode: String,
    val phone: String,
    val lastUpdate: String
)
        
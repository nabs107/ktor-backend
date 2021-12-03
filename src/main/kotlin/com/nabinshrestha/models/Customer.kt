package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val customerId: Int,
    val storeId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val addressId: Int,
    val isActive: Boolean,
    val createDate: String,
    val lastUpdate: String,
    val active: Int
)
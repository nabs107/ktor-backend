package com.nabinshrestha.models

import com.sun.org.apache.xpath.internal.operations.*
import kotlinx.serialization.Serializable

@Serializable
data class MenuAPI(
    val success: Boolean,
    val message: String,
    val connectWithUs: ArrayList<Menu>
)

@Serializable
data class Menu(
    val id: Int,
    val name: String,
    val code: String,
    val iconURLString: String,
    val routeURLString: String
)

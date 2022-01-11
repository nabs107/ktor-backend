package com.nabinshrestha.models

import kotlinx.serialization.Serializable

@Serializable
data class PromotionAPI (
    val success: Boolean,
    var message: String,
    val promotionList: ArrayList<Promotion>
)

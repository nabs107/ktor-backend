package com.nabinshrestha.routes

import com.nabinshrestha.database.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.promotionRoutes() {
    route("/promotions") {
        get {
            val promotionList: ArrayList<Promotion> = arrayListOf()
            transaction {
                Promotions.selectAll().map {
                    promotionList.add(
                        Promotion(
                            productId = it[Promotions.productId],
                            productName = it[Promotions.productName],
                            productDescription = it[Promotions.productDescription],
                            productURL = it[Promotions.productURL]
                        )
                    )
                }
            }
            call.respond(HttpStatusCode.OK, PromotionAPI(success = true, message = "Promotions Obtained Successfully", promotionList))
        }
    }
}

fun Application.registerPromotionRoutes() {
    routing {
        promotionRoutes()
    }
}
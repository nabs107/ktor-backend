package com.nabinshrestha.routes

import at.favre.lib.crypto.bcrypt.*
import com.nabinshrestha.database.*
import com.nabinshrestha.dtos.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.exceptions.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

fun Route.registerRoutes() {
    route("/register") {

        post {
            val register = call.receive<RegisterDTO>()
            register.email?.let { email ->
                register.password?.let { password ->
                    try {
                        transaction {
                            Users.insert {
                                it[Users.email] = email
                                val encryptedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray())
                                it[Users.password] = encryptedPassword
                            }
                        }
                        call.respond(HttpStatusCode.Created, APIModel(success = true, message = "User registered successfully."))
                    } catch (e: ExposedSQLException) {
                        call.respond(HttpStatusCode.Conflict, APIModel(success = false, message = e.localizedMessage))
                    }
                } ?: run {
                    call.respond(HttpStatusCode.OK, APIModel(success = false, message = "Password is required!"))
                }
            } ?: run {
                call.respond(HttpStatusCode.OK, APIModel(success = false, message = "Email is required!"))
            }
        }
    }
}

fun Application.registerRegisterRoutes() {
    routing {
        registerRoutes()
    }
}
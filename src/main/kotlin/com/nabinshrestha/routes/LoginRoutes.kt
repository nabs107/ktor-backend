package com.nabinshrestha.routes

import at.favre.lib.crypto.bcrypt.*
import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import com.nabinshrestha.database.*
import com.nabinshrestha.dtos.*
import com.nabinshrestha.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import java.util.*
import java.util.Date

fun Route.loginRoutes(audience: String, issuer: String, secret: String) {
    route("/login") {

        post {
            val user = call.receive<UserDTO>()
            var verified = false
            transaction {
                val password = Users.select { Users.email eq user.email }.single()[Users.password]
                val result = BCrypt.verifyer().verify(user.password.toCharArray(), password)
                verified = result.verified
            }
            if (verified) {
                val token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withClaim("username", user.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + 900000)) //milliseconds
                    .sign(Algorithm.HMAC256(secret))

                call.respond(hashMapOf("token" to token))
            } else {
                call.respond(HttpStatusCode.Forbidden, APIModel(success = false, message = "Username or Password is incorrect!"))
            }
        }
    }
}

fun Application.registerLoginRoutes() {
    val secret = "secret"
    val issuer = "http://0.0.0.0:8080/"
    val audience = "http://0.0.0.0:8080/hello"
    routing {
        loginRoutes(audience = audience, issuer = issuer, secret = secret)
    }
}
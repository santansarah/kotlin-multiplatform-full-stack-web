package routes

import domain.usecases.CheckForNewDescriptors
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.frontEnd() {

    route("/") {
        get {
            call.respondText(
                this::class.java.classLoader.getResource("index.html")!!.readText(),
                ContentType.Text.Html
            )
        }
    }

    static("/") {
        resources("")
    }

}
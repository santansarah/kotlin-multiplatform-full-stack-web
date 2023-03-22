package routes

import domain.CheckForNewDescriptors
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.main(
    checkForNewDescriptors: CheckForNewDescriptors
) {

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

    route("/descriptors") {
        get {
            val syncResponse = checkForNewDescriptors.invoke()
            call.respond(
                status = HttpStatusCode.OK,
                message = syncResponse
            )
        }
    }
}



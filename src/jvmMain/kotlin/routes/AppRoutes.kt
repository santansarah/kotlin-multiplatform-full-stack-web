package routes

import domain.usecases.CheckForNewCharacteristics
import domain.usecases.CheckForNewDescriptors
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.backEnd(
    checkForNewDescriptors: CheckForNewDescriptors,
    checkForNewCharacteristics: CheckForNewCharacteristics
) {

    route("/characteristics") {
        get {
            val syncResponse = checkForNewCharacteristics.invoke()
            call.respond(
                status = HttpStatusCode.OK,
                message = syncResponse
            )
        }
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



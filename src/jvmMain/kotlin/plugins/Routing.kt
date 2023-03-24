package plugins

import domain.usecases.CheckForNewCharacteristics
import domain.usecases.CheckForNewDescriptors
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import routes.backEnd
import routes.frontEnd

fun Application.configureRouting() {

    routing {

        val checkForNewCharacteristics: CheckForNewCharacteristics = get()
        val checkForNewDescriptors: CheckForNewDescriptors = get()

        frontEnd()
        backEnd(checkForNewDescriptors, checkForNewCharacteristics)
    }
}

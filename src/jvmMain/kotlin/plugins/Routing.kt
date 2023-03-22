package plugins

import data.SanTanScanDao
import data.remote.NumbersApiService
import domain.CheckForNewDescriptors
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import routes.main

fun Application.configureRouting() {

    routing {

        val checkForNewDescriptors: CheckForNewDescriptors = get()
        main(checkForNewDescriptors)
    }
}

package plugins

import data.SanTanScanDao
import data.remote.NumbersApiService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import routes.main

fun Application.configureRouting() {

    routing {

        val numbersApiService: NumbersApiService = get()
        val sanTanScanDao: SanTanScanDao = get()

        main(numbersApiService, sanTanScanDao)
    }
}

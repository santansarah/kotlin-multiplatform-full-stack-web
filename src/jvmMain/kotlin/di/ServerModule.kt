package di

import data.SanTanScanDao
import data.remote.NumbersApiService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.util.logging.*
import org.koin.dsl.module
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val serverModule = module {

    single {
        HttpClient {
            HttpClient(CIO) {
                expectSuccess = true
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("HTTP Client $message")
                        }
                    }
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
        }
    }

    single { NumbersApiService(get()) }
    single { SanTanScanDao() }

}
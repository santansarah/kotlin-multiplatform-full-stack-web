import data.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import plugins.configureKoin
import plugins.configureRouting
import shoppinglist.jvmMain.plugins.configureContentNegotiation

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    DatabaseFactory.init()

    configureKoin()
    configureContentNegotiation()
    configureRouting()
}

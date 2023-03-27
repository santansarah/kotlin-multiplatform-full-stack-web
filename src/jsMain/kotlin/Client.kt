import kotlinx.browser.document
import kotlinx.browser.window
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.getElementById("root") ?: error("Couldn't find container!")

    val viewPortSize = arrayOf(document.documentElement!!.clientWidth,
        document.documentElement!!.clientHeight)

    createRoot(container).render(App.create {
        gitPath = BASE_URL
        viewPort = viewPortSize
    })
}
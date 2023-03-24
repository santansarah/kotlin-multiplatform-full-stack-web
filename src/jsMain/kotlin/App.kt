import components.AppHeader
import components.Descriptors
import csstype.*
import emotion.react.css
import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span
import react.ChildrenBuilder

external interface AppProps : Props {
    var gitPath: String
    var accountBalance: Int
}

val App = FC<AppProps> {

    document.bgColor = "#0087ff"

    div {

        css {
            margin = Auto.auto
            width = 75.pct
            padding = 20.px
            height = 100.pc
            backgroundColor = Color("#f3f3f6")
            borderRadius = 50.px
        }

        AppHeader()

        gitPath(it.gitPath)

        hr {
            css {
                color = NamedColor.darkgray
            }
        }

        Descriptors()

    }

}

private fun ChildrenBuilder.gitPath(gitPath: String) {
    div {
        p {

            css {
                color = if (gitPath.contains("ktor-testing"))
                    NamedColor.blue
                else
                    NamedColor.green
            }

            span {
                css {
                    fontWeight = csstype.FontWeight.Companion.bold
                }
                +"Git Path: "
            }
            +gitPath
        }
    }
}
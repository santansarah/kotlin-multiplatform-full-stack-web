import components.AppHeader
import components.Descriptors
import csstype.*
import emotion.react.css
import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span
import react.ChildrenBuilder
import react.dom.html.HTMLAttributes
import css.*

external interface AppProps : Props {
    var gitPath: String
    var viewPort: Array<Int>
}

val App = FC<AppProps> {

    document.bgColor = "#0087ff"

    div {

        containerCss()

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
                wordWrap = WordWrap.breakWord
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
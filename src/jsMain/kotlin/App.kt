import csstype.*
import emotion.react.css
import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.ReactHTML.body
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span

external interface AppProps : Props {
    var gitPath: String
    var accountBalance: Int
}

val App = FC<AppProps> {

    document.bgColor = "#0087ff"

    div {

        css(ClassName("kotlin-emotion")) {
            margin = Auto.auto
            width = 70.pct
            padding = 10.px
            fontSize = 22.pt
            color = NamedColor.white
            fontWeight = FontWeight.bold
            border = Border(4.px, LineStyle.solid)
            borderColor = Color("purple")
            backgroundImage = linearGradient(angle = 90.deg, Color("#8150fe"), Color("#e3465b"))
            borderRadius = 6.em
            boxSizing = BoxSizing.borderBox
            //position = Position.fixed
            opacity = number(.75)
            overflow = Overflow.hidden
            visibility = Visibility.visible
        }

        +"Welcome to the kotlin-emotion wrapper"

    }

    div {

        css {
            margin = Auto.auto
            width = 65.pc
            padding = 20.px
            height = 100.pc
            backgroundColor = Color("#e2e2ef")
            borderRadius = 50.px
        }

        AppHeader()

        div {
            p {

                /*css {
                    color = if (it.accountBalance >= 0 )
                        NamedColor.green
                    else
                        NamedColor.red
                }*/

                css {
                    color = if (it.gitPath.contains("ktor-testing"))
                        NamedColor.blue
                    else
                        NamedColor.green
                }

                span {
                    css {
                        fontWeight = FontWeight.bold
                    }
                    +"Git Path: "
                }
                +it.gitPath
            }
        }

        hr {
            css {
                color = NamedColor.darkgray
            }
        }

        Descriptors()

    }

}
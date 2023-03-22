import csstype.*
import emotion.react.css
import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.ReactHTML.body
import react.dom.html.ReactHTML.div

val App = FC<Props> {

    document.bgColor = "#0087ff"

    div {

        css {
            margin = Auto.auto
            width = 50.pc
            padding = 10.px
            fontSize = 22.pt
            color = NamedColor.white
            fontWeight = FontWeight.bold
            border = Border(1.px, LineStyle.solid)
            borderColor = NamedColor.purple
            backgroundImage = linearGradient(angle = 90.deg, Color("#8150fe"), Color("#e3465b"))
            borderRadius = 6.em
        }

        +"Welcome to the kotlin-emotion wrapper"

    }

    div {

        css {
            fontFace {
                this.fontFamily = "OpenSans"
                this.src = "url(./fonts/opensans.ttf)"
            }
            margin = Auto.auto
            width = 65.pc
            padding = 20.px
            height = 100.pc
            fontFamily = "OpenSans".unsafeCast<FontFamily>()
            backgroundColor = Color("#e2e2ef")
            borderRadius = 50.px
            /*fontFace {
            this.fontFamily = "Roboto"
            this.src = "./fonts/roboto.ttf"
        }*/
        }

        AppHeader()

    }

}
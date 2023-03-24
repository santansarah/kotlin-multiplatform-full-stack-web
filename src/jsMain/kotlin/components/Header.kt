package components

import csstype.Display
import csstype.px
import csstype.rgb
import react.FC
import react.Props
import emotion.react.css
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.input
import react.useState

val AppHeader = FC<Props> { props ->
    div {

        css {
            display = Display.flex
        }

        img {

            css {
                width = 80.px
                paddingRight = 20.px
            }

            src = "./images/santanscan-logo-print.png"
        }

        h1 {
            +"SanTanScan Numbers DB Sync"
        }
    }
}
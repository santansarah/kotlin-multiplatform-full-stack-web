package css

import csstype.*
import emotion.react.css
import org.w3c.dom.HTMLDivElement
import react.dom.html.HTMLAttributes

val DESKTOP = Selector("@media(max-width: 1240px)")
val TABLET = Selector("@media(max-width: 850px)")
val MOBILE = Selector("@media(max-width: 480px)")

fun HTMLAttributes<HTMLDivElement>.containerCss() {
    css {
        margin = Auto.auto
        padding = 20.px
        height = 100.pc
        backgroundColor = Color("#f7f7f8")
        border = Border(4.px, LineStyle.solid, Color("#00005d"))
        borderRadius = 50.px
        width = 60.pct
        DESKTOP {
            width = 70.pct
        }
        TABLET {
            width = 80.pct
        }
        MOBILE {
            width = 90.pct
        }
    }
}

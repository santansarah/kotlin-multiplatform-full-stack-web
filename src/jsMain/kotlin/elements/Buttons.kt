package elements

import csstype.Color
import csstype.JustifySelf
import csstype.px
import emotion.react.css
import org.w3c.dom.HTMLButtonElement
import react.ChildrenBuilder
import react.dom.html.ButtonHTMLAttributes
import react.dom.html.ReactHTML

private fun ButtonHTMLAttributes<HTMLButtonElement>.buttonClass() {
    css {
        borderRadius = 20.px
        width = 200.px
        backgroundColor = Color("#0087ff")
        color = Color("#e2e2ef")
        justifySelf = JustifySelf.selfEnd
        padding = 10.px
    }
}

fun ChildrenBuilder.appButton(text: String, buttonEvent: () -> Unit) {
    ReactHTML.button {
        buttonClass()
        +text
        onClick = {
            buttonEvent.invoke()
        }
    }
}


import csstype.*
import emotion.react.css
import org.w3c.dom.HTMLButtonElement
import react.ChildrenBuilder
import react.FC
import react.Props
import react.dom.html.ButtonHTMLAttributes
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.textarea
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.useState

external interface DescriptorsProps: Props {

}

private val scope = MainScope()

val Descriptors = FC<Props> { props ->

    var descriptorSyncResponse: SyncResponse? by useState(SyncResponse(0, listOf(
        Descriptor("", "Click to Sync", "gss", "20F0")
    ), emptyList()))

    fun onSync() {
        scope.launch {
            descriptorSyncResponse = syncDescriptors()
        }
    }

    div {
        div {
            css {
                display = Display.flex
                alignItems = AlignItems.center
            }

            h2 {

                css {
                    width = 50.pct
                }

                +"Sync Descriptors"
            }

            appButton("Start Sync", { onSync() })
        }
        p {
            +"API Results"
        }
        textarea {

            css {
                backgroundColor = Color("#d2d6db")
                display = Display.block
                width = 100.pct
            }

            rows = 6
            value = descriptorSyncResponse?.toString() ?: ""
        }
    }


}

private fun PropertiesBuilder.commonProperties() {
    display = Display.flex
    padding = 14.px
    alignItems = AlignItems.center
}

fun ChildrenBuilder.appButton(text: String, buttonEvent: () -> Unit) {
    button {
        buttonClass()
        +text
        onClick = {
            buttonEvent.invoke()
        }
    }
}

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

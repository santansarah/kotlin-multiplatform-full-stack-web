package components

import Descriptor
import SyncResponse
import csstype.*
import elements.appButton
import emotion.react.css
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.ChildrenBuilder
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.textarea
import react.useState
import services.syncDescriptors

external interface DescriptorsProps : Props {

}

private val scope = MainScope()

val Descriptors = FC<Props> { props ->

    var descriptorSyncResponse by useState("")

    fun onSync() {
        println("button event launched")
        scope.launch {
            val responseString = syncDescriptors()
            descriptorSyncResponse = responseString.updateCount.toString() + "\n" +
                    responseString.updatedValues.toString() + "\n" +
                    responseString.errors.toString()
        }
    }

    syncItem(
        title = "Sync Descriptors",
        descriptorSyncResponse = descriptorSyncResponse,
        onSync = { onSync() }
    )


}

fun ChildrenBuilder.syncItem(
    title: String,
    descriptorSyncResponse: String,
    onSync: () -> Unit
) {
    div {
        div {
            css {
                display = Display.flex
                flexWrap = FlexWrap.wrap
                alignItems = AlignItems.center
            }

            h2 {

                css {
                    width = 50.pct
                }

                +title
            }

            appButton("Start Sync", onSync)
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
            value = descriptorSyncResponse
        }
    }
}


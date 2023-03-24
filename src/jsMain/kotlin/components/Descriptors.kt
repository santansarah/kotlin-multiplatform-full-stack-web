package components

import Descriptor
import SyncResponse
import csstype.*
import elements.appButton
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.textarea
import react.useState
import services.syncDescriptors

external interface DescriptorsProps: Props {

}

private val scope = MainScope()

val Descriptors = FC<Props> { props ->

    var descriptorSyncResponse: SyncResponse? by useState(
        SyncResponse(
            0, listOf(
                Descriptor("", "Click to Sync", "gss", "20F0")
            ), emptyList()
        )
    )

    fun onSync() {
        scope.launch {
            descriptorSyncResponse = syncDescriptors()
        }
    }

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

                +"Sync Descriptors"
            }

            appButton("Start Sync") { onSync() }
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


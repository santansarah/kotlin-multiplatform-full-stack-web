package elements

import csstype.*
import emotion.react.css
import react.ChildrenBuilder
import react.dom.html.ReactHTML

fun ChildrenBuilder.syncItem(
    title: String,
    descriptorSyncResponse: String,
    onSync: () -> Unit
) {
    ReactHTML.div {
        ReactHTML.div {
            css {
                display = Display.flex
                flexWrap = FlexWrap.wrap
                alignItems = AlignItems.center
            }

            ReactHTML.h2 {

                css {
                    width = 50.pct
                }

                +title
            }

            appButton("Start Sync", onSync)
        }
        ReactHTML.p {
            +"API Results"
        }
        ReactHTML.textarea {

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
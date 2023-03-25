package components

import Descriptor
import SyncResponse
import csstype.*
import elements.appButton
import elements.syncItem
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
import services.toApiResults

external interface DescriptorsProps : Props {

}

private val scope = MainScope()

val Descriptors = FC<Props> { props ->

    var descriptorSyncResponse by useState("")

    fun onSync() {
        println("button event launched")
        scope.launch {
            descriptorSyncResponse = syncDescriptors().toApiResults()
        }
    }

    syncItem(
        title = "Sync Descriptors",
        descriptorSyncResponse = descriptorSyncResponse,
        onSync = { onSync() }
    )

}



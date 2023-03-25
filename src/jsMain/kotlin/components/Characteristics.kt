package components

import SyncResponse
import elements.syncItem
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.useState
import services.syncCharacteristics
import services.syncDescriptors
import services.toApiResults

external interface CharacteristicsProps : Props {

}

private val scope = MainScope()

val Characteristics = FC<Props> { props ->

    var characteristicsSyncResponse by useState("")

    fun onSync() {
        println("button event launched")
        scope.launch {
            characteristicsSyncResponse = syncCharacteristics().toApiResults()
        }
    }

    syncItem(
        title = "Sync Characteristics",
        descriptorSyncResponse = characteristicsSyncResponse,
        onSync = { onSync() }
    )

}




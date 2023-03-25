package services

import BleCharacteristic
import Descriptor
import SyncResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun syncCharacteristics(): SyncResponse<BleCharacteristic> {
    return jsonClient.get(BleCharacteristic.path).body()
}


suspend fun syncDescriptors(): SyncResponse<Descriptor> {
    return jsonClient.get(Descriptor.path).body()
}

fun <T> SyncResponse<T>.toApiResults(): String =
    "updateCount: ${this.updateCount}\n" +
            "updateRows: ${this.updatedValues}\n" +
            "updateErrors: ${this.errors}"


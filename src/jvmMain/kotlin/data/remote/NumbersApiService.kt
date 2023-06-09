package data.remote

import BASE_URL
import BleCharacteristic
import Descriptor
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import kotlinx.serialization.decodeFromString
import ServiceResult

class NumbersApiService constructor(
    private val client: HttpClient
) : KoinComponent {

    companion object ApiDefaults {
        const val DESCRIPTORS = "$BASE_URL/${Descriptor.jsonFile}"
        const val CHARACTERISTICS = "$BASE_URL/${BleCharacteristic.jsonFile}"
    }

    suspend fun getCharacteristicsJson(): ServiceResult<List<BleCharacteristic>> {

        return try {

            val characteristicJson: String = client.get(CHARACTERISTICS).body()

            println(characteristicJson)

            val characteristicList: List<BleCharacteristic> = Json.decodeFromString(characteristicJson)
            println(characteristicList.toString())

            ServiceResult.Success(characteristicList)

        } catch (apiError: Exception) {
            println(apiError.message)
            return ServiceResult.Error(ErrorCode.FETCH_ERROR)
        }
    }

    suspend fun getDescriptorsJson(): ServiceResult<List<Descriptor>> {

        return try {

           val descriptorJson: String = client.get(DESCRIPTORS).body()

            println(descriptorJson)

            val descriptorList: List<Descriptor> = Json.decodeFromString(descriptorJson)
            println(descriptorList.toString())

            ServiceResult.Success(descriptorList)

        } catch (apiError: Exception) {
            println(apiError.message)
            return ServiceResult.Error(ErrorCode.FETCH_ERROR)
        }
    }
}
package data.remote

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

       // private const val BASE_URL = "https://raw.githubusercontent.com/santansarah/bluetooth-numbers-database/master/v1"
        private const val BASE_URL = "https://raw.githubusercontent.com/santansarah/bluetooth-numbers-database/ktor-testing/v1"
        const val DESCRIPTORS = "$BASE_URL/descriptor_uuids.json"

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
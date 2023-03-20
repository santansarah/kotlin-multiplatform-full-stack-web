import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val code: Int,
    val name: String
)

@Serializable
data class Service(
    val identifier: String,
    val name: String,
    val source: String,
    val uuid: String
)

@Serializable
data class BleCharacteristic(
    val identifier: String,
    val name: String,
    val source: String,
    val uuid: String
)

@Serializable
data class Descriptor(
    val identifier: String,
    val name: String,
    val source: String,
    val uuid: String
)
import kotlinx.serialization.Serializable

@Serializable
data class SyncResponse(
    val updateCount: Int,
    val updatedValues: List<Descriptor>,
    val errors: List<ResponseErrors>
)

@Serializable
data class ResponseErrors(val code: ErrorCode, val message: String)

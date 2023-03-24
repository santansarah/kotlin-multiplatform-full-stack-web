import kotlinx.serialization.Serializable

@Serializable
data class SyncResponse<T>(
    val updateCount: Int,
    val updatedValues: List<T>,
    val errors: List<ResponseErrors>
)

@Serializable
data class ResponseErrors(val code: ErrorCode, val message: String)

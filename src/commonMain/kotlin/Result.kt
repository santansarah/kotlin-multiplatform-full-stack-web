sealed class ServiceResult<out R> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    data class Error(val error: ErrorCode) : ServiceResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val ServiceResult<*>.succeeded
    get() = this is ServiceResult.Success && data != null

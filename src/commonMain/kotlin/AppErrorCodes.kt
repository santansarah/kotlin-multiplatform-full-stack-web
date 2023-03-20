

enum class ErrorCode(val message: String) {
    DATABASE_ERROR("Unknown database error. Try again, and check your parameters."),
    FETCH_ERROR("Couldn't get RAW JSON."),
    INVALID_JSON("Your JSON must match the format in this sample response."),
}

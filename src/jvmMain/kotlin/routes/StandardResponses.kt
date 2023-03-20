package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import SyncResponse
import Descriptor
import ResponseErrors

suspend fun PipelineContext<Unit, ApplicationCall>.respondWithError(
    serviceResult: ServiceResult.Error

) {
    call.respond(
        status = HttpStatusCode.BadRequest,
        message =
        SyncResponse(
            0, emptyList(), listOf(
                ResponseErrors(serviceResult.error, serviceResult.error.message)
            )
        )
    )
}
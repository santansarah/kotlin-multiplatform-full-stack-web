package domain

import Descriptor
import ResponseErrors
import ServiceResult
import SyncResponse

fun syncError(serviceResult: ServiceResult.Error) = SyncResponse(
    0, emptyList(), listOf(
        ResponseErrors(serviceResult.error, serviceResult.error.message)
    )
)

fun syncDescriptorSuccess(newDescriptors: List<Descriptor>) = SyncResponse(
    newDescriptors.count(), newDescriptors, emptyList()
)
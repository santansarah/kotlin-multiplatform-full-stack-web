package domain

import Descriptor
import ResponseErrors
import ServiceResult
import SyncResponse

fun <T> syncError(serviceResult: ServiceResult.Error) = SyncResponse(
    0, emptyList<T>(), listOf(
        ResponseErrors(serviceResult.error, serviceResult.error.message)
    )
)

fun <T> syncSuccess(newList: List<T>) = SyncResponse(
    newList.count(), newList, emptyList()
)
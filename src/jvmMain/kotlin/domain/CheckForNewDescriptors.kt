package domain

import Descriptor
import Service
import ServiceResult
import SyncResponse
import data.SanTanScanDao
import data.remote.NumbersApiService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CheckForNewDescriptors
    (
    private val sanTanScanDao: SanTanScanDao,
    private val numbersApiService: NumbersApiService
) {
    suspend operator fun invoke(): SyncResponse {

        val existingDescriptorsFromDb = sanTanScanDao.getDescriptors()
        if (existingDescriptorsFromDb is ServiceResult.Error) {
            return syncError(existingDescriptorsFromDb)
        }


        val jsonDescriptorsFromGit = numbersApiService.getDescriptorsJson()
        if (jsonDescriptorsFromGit is ServiceResult.Error)
            return syncError(jsonDescriptorsFromGit)

        val newDescriptors: List<Descriptor> =
            (jsonDescriptorsFromGit as ServiceResult.Success)
                .data.minus(
                    (existingDescriptorsFromDb as ServiceResult.Success)
                        .data.toSet()
                )

        println(newDescriptors.toString())

        return if (newDescriptors.isNotEmpty()) {
            val insertBatch = sanTanScanDao.batchDescriptorInsert(newDescriptors)
            if (insertBatch is ServiceResult.Error)
                syncError(insertBatch)
            else {
                syncDescriptorSuccess(newDescriptors)
            }
        } else {
            syncDescriptorSuccess(emptyList())
        }

    }
}
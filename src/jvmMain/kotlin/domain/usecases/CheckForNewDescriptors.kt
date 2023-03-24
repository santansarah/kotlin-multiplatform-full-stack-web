package domain.usecases

import Descriptor
import ServiceResult
import SyncResponse
import data.SanTanScanDao
import data.remote.NumbersApiService
import domain.syncError
import domain.syncSuccess

class CheckForNewDescriptors
    (
    private val sanTanScanDao: SanTanScanDao,
    private val numbersApiService: NumbersApiService
) {
    suspend operator fun invoke(): SyncResponse<Descriptor> {

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
                syncSuccess(newDescriptors)
            }
        } else {
            syncSuccess(emptyList())
        }

    }
}
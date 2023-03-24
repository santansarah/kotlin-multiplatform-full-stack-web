package domain.usecases

import BleCharacteristic
import ServiceResult
import SyncResponse
import data.SanTanScanDao
import data.remote.NumbersApiService
import domain.syncError
import domain.syncSuccess

class CheckForNewCharacteristics
    (
    private val sanTanScanDao: SanTanScanDao,
    private val numbersApiService: NumbersApiService
) {
    suspend operator fun invoke(): SyncResponse<BleCharacteristic> {

        val existingCharacteristicsFromDb = sanTanScanDao.getCharacteristics()
        if (existingCharacteristicsFromDb is ServiceResult.Error) {
            return syncError(existingCharacteristicsFromDb)
        }


        val jsonCharacteristicsFromGit = numbersApiService.getCharacteristicsJson()
        if (jsonCharacteristicsFromGit is ServiceResult.Error)
            return syncError(jsonCharacteristicsFromGit)

        val newCharacteristics: List<BleCharacteristic> =
            (jsonCharacteristicsFromGit as ServiceResult.Success)
                .data.minus(
                    (existingCharacteristicsFromDb as ServiceResult.Success)
                        .data.toSet()
                )

        println(newCharacteristics.toString())

        return if (newCharacteristics.isNotEmpty()) {
            val insertBatch = sanTanScanDao.batchCharacteristicsInsert(newCharacteristics)
            if (insertBatch is ServiceResult.Error)
                syncError(insertBatch)
            else {
                syncSuccess(newCharacteristics)
            }
        } else {
            syncSuccess(emptyList())
        }

    }
}
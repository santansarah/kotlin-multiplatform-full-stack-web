package routes

import Descriptor
import ResponseErrors
import ServiceResult
import SyncResponse
import data.SanTanScanDao
import data.remote.NumbersApiService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun Route.main(
    numbersApiService: NumbersApiService,
    sanTanScanDao: SanTanScanDao
) {

    route("/descriptors") {
        get {

            when (val databaseDescriptors = sanTanScanDao.getDescriptors()){
                is ServiceResult.Success -> {

                    println(databaseDescriptors.data.toString())

                    when (val jsonDescriptors = numbersApiService.getDescriptorsJson()) {
                        is ServiceResult.Success -> {

                            val notInDescriptorList: List<Descriptor> =
                                jsonDescriptors.data.minus(databaseDescriptors.data.toSet())
                            println(notInDescriptorList.toString())

                            call.respond(
                                status = HttpStatusCode.OK,
                                message =
                                SyncResponse(
                                    notInDescriptorList.count(), notInDescriptorList, emptyList()
                                )
                            )
                        }
                        is ServiceResult.Error -> {
                            respondWithError(jsonDescriptors)
                        }
                    }

                }
                is ServiceResult.Error -> {
                    respondWithError(databaseDescriptors)
                }
            }

        }
    }
}



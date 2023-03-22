package data

import Descriptor
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ServiceResult
import data.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*

class SanTanScanDao {
    private fun resultRowToDescriptor(row: ResultRow): Descriptor {
      return try {
            Descriptor(
                identifier = row[DescriptorTable.identifier],
                name = row[DescriptorTable.name],
                uuid = row[DescriptorTable.uuid],
                source = row[DescriptorTable.bleSource]
            )
        } catch (e: Exception) {
            println(e)
          return Descriptor("","","","")
        }
    }

    suspend fun getDescriptors(): ServiceResult<List<Descriptor>> {
        return try {
            val descriptors = dbQuery {
                DescriptorTable.selectAll()
                    .map(::resultRowToDescriptor)
            }

            ServiceResult.Success(descriptors)

        } catch (e: Exception) {
            println(e)
            ServiceResult.Error(ErrorCode.DATABASE_ERROR)
        }
    }

    suspend fun batchDescriptorInsert(descriptors: List<Descriptor>): ServiceResult<Boolean> {
        return try {
            val result = dbQuery {
                DescriptorTable.batchInsert(descriptors) {
                    this[DescriptorTable.uuid] = it.uuid
                    this[DescriptorTable.name] = it.name
                    this[DescriptorTable.identifier] = it.identifier
                    this[DescriptorTable.bleSource] = it.source
                }
            }

            ServiceResult.Success(true)

        } catch (e: Exception) {
            println(e)
            ServiceResult.Error(ErrorCode.DATABASE_ERROR)
        }
    }

}
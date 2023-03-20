package data

import Descriptor
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import ServiceResult
import data.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

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

}
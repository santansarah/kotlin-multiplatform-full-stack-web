package data

import org.jetbrains.exposed.sql.Table


object CharacteristicTable : Table("characteristics") {
    val name = varchar("name", 255)
    val identifier = varchar("identifier", 255)
    val uuid = varchar("uuid", 255)
    val bleSource = varchar("source", length = 255)

    override val primaryKey = PrimaryKey(identifier)
}

object DescriptorTable : Table("descriptors") {
    val name = varchar("name", 255)
    val identifier = varchar("identifier", 255)
    val uuid = varchar("uuid", 255)
    val bleSource = varchar("source", length = 255)

    override val primaryKey = PrimaryKey(bleSource)
}

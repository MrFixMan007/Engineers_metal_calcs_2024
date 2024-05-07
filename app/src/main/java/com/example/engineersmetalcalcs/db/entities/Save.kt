package com.example.engineersmetalcalcs.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

@Entity(tableName = "save", foreignKeys = [ForeignKey(entity = Type::class,
    parentColumns = ["id"], childColumns = ["typeIdFk"], onDelete = ForeignKey.CASCADE)]
)
data class Save(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var name: String,
    var date: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()),
    var typeIdFk: Long,
    var description: String = ""
)
{
    fun changeName(name: String) {
        if (name.length <= 100) this.name = name else this.name = name.substring(0, 100)
    }
    fun changeDescription(description: String) {
        if (description.length <= 300) this.description = description else this.description =
            description.substring(0, 300)
    }
    fun changeTypeIdFk(typeIdFk: Long) {
        if (typeIdFk > 0) this.typeIdFk = typeIdFk
    }
}

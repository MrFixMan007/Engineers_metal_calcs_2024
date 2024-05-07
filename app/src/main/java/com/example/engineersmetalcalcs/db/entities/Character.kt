package com.example.engineersmetalcalcs.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "character", foreignKeys = [ForeignKey(entity = Type::class,
    parentColumns = ["id"], childColumns = ["typeIdFk"], onDelete = ForeignKey.CASCADE)]
)
data class Character(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(index = true)
    var typeIdFk: Long,
    @ColumnInfo(index = true)
    var name: String,
    var low: Float,
    var top: Float,
    var description: String,
    var measuredIn: String,
    var defaultValue: Float,
    var koef: Float,
    var isInput: Boolean = true,
    var isStrongMeasure: Boolean = false
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
    fun changeMeasuredIn(measuredIn: String) {
        if (measuredIn.length <= 45) this.measuredIn = measuredIn else
            this.measuredIn = measuredIn.substring(0, 45)
    }
}
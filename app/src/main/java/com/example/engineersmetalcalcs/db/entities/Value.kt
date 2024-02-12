package com.example.engineersmetalcalcs.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "value", foreignKeys = [
    ForeignKey(entity = Character::class,
    parentColumns = ["id"], childColumns = ["characterIdFk"]),
    ForeignKey(entity = Save::class,
        parentColumns = ["id"], childColumns = ["saveIdFk"])
    ]
)
data class Value(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var characterIdFk: Long,
    var saveIdFk: Long,
    var value: Float,
    var measuredIn: String = ""
)

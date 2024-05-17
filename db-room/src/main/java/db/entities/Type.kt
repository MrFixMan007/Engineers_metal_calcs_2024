package db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class Type(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(index = true)
    var name: String
)
{
    fun changeName(name: String) {
        if (name.length <= 100) this.name = name else this.name = name.substring(0, 100)
    }
}

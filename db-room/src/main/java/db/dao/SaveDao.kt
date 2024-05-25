package db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import db.entities.Save

@Dao
interface SaveDao {
    @Insert
    fun insert(obj: Save): Long
    @Insert
    fun insertSome(obj: List<Save>): LongArray?
    @Delete
    fun delete(obj: Save)
    @Delete
    fun deleteSome(obj: List<Save>): Int
    @Update
    fun update(obj: Save)
    @Update
    fun updateSome(obj: List<Save>): Int

    @Query("SELECT * FROM save WHERE id = :id")
    fun getById(id: Long): Save
    @Query("SELECT * FROM save WHERE typeIdFk = :id")
    fun getByTypeIdFk(id: Long): List<Save>
    @get:Query("SELECT * FROM save")
    val all: List<Save>
    @get:Query("SELECT COUNT(*) FROM save")
    val countOfSaves: Int
    @get:Query("SELECT * FROM save ORDER BY ID DESC LIMIT 1")
    val last: Save
    @Query("DELETE FROM save WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM save WHERE name = :name AND description = :description AND date = :date AND result = :result")
    fun getByNameDescriptionDateResult(name: String, description: String, date: String, result: String): Save
}
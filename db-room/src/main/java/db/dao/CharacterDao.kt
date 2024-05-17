package db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import db.entities.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character WHERE id = :id")
    fun getById(id: Long): Character

    @get:Query("SELECT * FROM character")
    val all: List<Character>
    @Query("SELECT * FROM character WHERE typeIdFk = :id")
    fun getAllByTypeIdFk(id: Long): List<Character>

    @Query("DELETE FROM character")
    fun deleteAll()

    @get:Query("SELECT COUNT(*) FROM character")
    val countOfCharacters: Int

    @Query("SELECT * FROM character WHERE typeIdFk = :id AND isInput = 0")
    fun getResults(id: Long): List<Character>

    @Insert
    fun insert(obj: Character): Long
    @Insert
    fun insertSome(obj: List<Character>): LongArray?
    @Delete
    fun delete(obj: Character)
    @Delete
    fun deleteSome(obj: List<Character>): Int
    @Update
    fun update(obj: Character)
    @Update
    fun updateSome(obj: List<Character>): Int
}
package com.example.engineersmetalcalcs.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.engineersmetalcalcs.db.entities.Value

@Dao
interface ValueDao {
    @Query("SELECT * FROM value WHERE id = :id")
    fun getById(id: Long): Value
    @Query("SELECT * FROM value WHERE saveIdFk = :saveId AND characterIdFk = :characterId")
    fun getByCharacterIdFkAndSaveIdFk(characterId: Long, saveId: Long): Value

    @get:Query("SELECT * FROM value")
    val all: List<Value>

    @get:Query("SELECT COUNT(*) FROM value")
    val countOfCharacters: Int

    @Insert
    fun insert(obj: Value): Long
    @Insert
    fun insertSome(obj: List<Value>): LongArray?
    @Delete
    fun delete(obj: Value)
    @Delete
    fun deleteSome(obj: List<Value>): Int
    @Update
    fun update(obj: Value)
    @Update
    fun updateSome(obj: List<Value>): Int
}
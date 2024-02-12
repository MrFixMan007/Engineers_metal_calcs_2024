package com.example.engineersmetalcalcs.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.engineersmetalcalcs.db.entities.PossibleValue

@Dao
interface PossibleValueDao {
    @Query("SELECT * FROM possible_value WHERE id = :id")
    fun getById(id: Long): PossibleValue

    @get:Query("SELECT * FROM possible_value")
    val all: List<PossibleValue>

    @get:Query("SELECT COUNT(*) FROM possible_value")
    val countOfCharacters: Int

    @Insert
    fun insert(obj: PossibleValue): Long
    @Insert
    fun insertSome(obj: List<PossibleValue>): LongArray?
    @Delete
    fun delete(obj: PossibleValue)
    @Delete
    fun deleteSome(obj: List<PossibleValue>): Int
    @Update
    fun update(obj: PossibleValue)
    @Update
    fun updateSome(obj: List<PossibleValue>): Int
}
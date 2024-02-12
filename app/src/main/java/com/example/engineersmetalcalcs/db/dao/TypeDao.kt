package com.example.engineersmetalcalcs.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.engineersmetalcalcs.db.entities.Type

@Dao
interface TypeDao {
    @Query("SELECT * FROM type WHERE id = :id")
    fun getById(id: Long): Type

    @get:Query("SELECT * FROM type")
    val all: List<Type>

    @get:Query("SELECT COUNT(*) FROM type")
    val countOfTypes: Int

    @Insert
    fun insert(obj: Type): Long
    @Insert
    fun insertSome(obj: List<Type>): LongArray?
    @Delete
    fun delete(obj: Type)
    @Delete
    fun deleteSome(obj: List<Type>): Int
    @Update
    fun update(obj: Type)
    @Update
    fun updateSome(obj: List<Type>): Int
}
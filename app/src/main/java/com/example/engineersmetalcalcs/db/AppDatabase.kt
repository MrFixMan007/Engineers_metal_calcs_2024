package com.example.engineersmetalcalcs.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.engineersmetalcalcs.db.dao.CharacterDao
import com.example.engineersmetalcalcs.db.dao.PossibleValueDao
import com.example.engineersmetalcalcs.db.dao.SaveDao
import com.example.engineersmetalcalcs.db.dao.TypeDao
import com.example.engineersmetalcalcs.db.dao.ValueDao
import com.example.engineersmetalcalcs.db.entities.Character
import com.example.engineersmetalcalcs.db.entities.PossibleValue
import com.example.engineersmetalcalcs.db.entities.Save
import com.example.engineersmetalcalcs.db.entities.Type
import com.example.engineersmetalcalcs.db.entities.Value
import com.example.engineersmetalcalcs.R

@Database(entities = [Save::class, Type::class,
    Character::class, Value::class, PossibleValue::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun saveDao(): SaveDao?
    abstract fun typeDao(): TypeDao?
    abstract fun characterDao(): CharacterDao?
    abstract fun valueDao(): ValueDao?
    abstract fun possibleValueDao(): PossibleValueDao?
    companion object {
        private var instance: AppDatabase? = null

        const val ID_RESULT: Long = 1
        const val ID_KOEF1: Long = 2
        const val ID_KOEF2: Long = 3
        const val ID_KOEF3: Long = 4
        const val ID_KOEF4: Long = 5

        const val ID_TYPE_FASON: Long = 1
        const val ID_TYPE_INGOT: Long = 2
        const val ID_TYPE_SIMPLE_GATING_SYSTEM: Long = 3
        const val ID_TYPE_ADVANCED_GATING_SYSTEM: Long = 4
        const val ID_TYPE_CLASSIC_PROFIT1: Long = 5
        const val ID_TYPE_CLASSIC_PROFIT2: Long = 6
        const val ID_TYPE_CLASSIC_PROFIT3: Long = 7
        const val ID_TYPE_EXO_PROFIT: Long = 8
        const val ID_TYPE_SIDE_PROFIT1: Long = 9
        const val ID_TYPE_WEIGHT_CARGO: Long = 10
        const val ID_TYPE_TIME_EXPOSURE: Long = 11
        const val ID_TYPE_CHARGE: Long = 12

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "database"
                )
                    .build()
                    .setDefaultValues(context)
//                instance!!.setDefaultValues()
            }
            return instance
        }
    }

    fun setDefaultValues(context: Context): AppDatabase? {
        val typeDao = typeDao()
        val typesArray = context.resources.getStringArray(R.array.types)
        val types = ArrayList<Type>()
        for(item in typesArray){
            types.add(Type(null, item))
        }
        typeDao?.insertSome(types)

        val characterDao = characterDao()
        val descArray = context.resources.getStringArray(R.array.weightCargoArrayWith)
        val descArray1 = context.resources.getStringArray(R.array.weightCargoArrayWithout)
        val characterList = listOf(
            Character(null, ID_TYPE_WEIGHT_CARGO, "V", 0f, 10000f, descArray[0], context.getString(R.string.m3), 0f, 1f),
            Character(null, ID_TYPE_WEIGHT_CARGO, "K1", 0f, 100f, descArray[1], "", 0f, 1f),
            Character(null, ID_TYPE_WEIGHT_CARGO, "P", 0f, 10000f, descArray[2], context.getString(R.string.t), 0f, 1f, false),
            Character(null, ID_TYPE_WEIGHT_CARGO, "V1", 0f, 10000f, descArray1[0], context.getString(R.string.m3), 0f, 1f),
            Character(null, ID_TYPE_WEIGHT_CARGO, "V2", 0f, 10000f, descArray1[1], context.getString(R.string.m3), 0f, 1f),
            Character(null, ID_TYPE_WEIGHT_CARGO, "K", 0f, 10000f, descArray1[2], "", 0f, 1f),
            Character(null, ID_TYPE_WEIGHT_CARGO, "k1", 0f, 10000f, descArray1[3], "", 0f, 1f),
            Character(null, ID_TYPE_WEIGHT_CARGO, "k2", 0f, 10000f, descArray1[4], "", 0f, 1f),
            Character(null, ID_TYPE_WEIGHT_CARGO, "P", 0f, 10000f, descArray1[5], context.getString(R.string.m3), 0f, 1f, false),
            )
        characterDao?.insertSome(characterList)

        return this
    }
}
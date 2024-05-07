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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [Save::class, Type::class,
    Character::class, Value::class, PossibleValue::class],
    version = 10)
abstract class AppDatabase : RoomDatabase() {
    abstract fun saveDao(): SaveDao?
    abstract fun typeDao(): TypeDao?
    abstract fun characterDao(): CharacterDao?
    abstract fun valueDao(): ValueDao?
    abstract fun possibleValueDao(): PossibleValueDao?
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        var mapTypes = mutableMapOf<String, Type>()

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = databaseBuilder(
                    context,
                    AppDatabase::class.java, "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                CoroutineScope(Dispatchers.IO).launch {
                    instance?.setDefaultValues(context)
                }
            }
            return instance!!
        }
    }

    private suspend fun setDefaultValues(context: Context) = withContext(Dispatchers.IO){

        val typeDao = typeDao()
        if (typeDao?.all?.size == 0) {
            val typesArray = context.resources.getStringArray(R.array.types)
            val types = ArrayList<Type>()
            for (item in typesArray) {
                types.add(Type(null, item))
            }
            typeDao.insertSome(types)
            val types1 = typeDao.all
            for (item in types1) {
                mapTypes[item.name] = item
            }

            val characterDao = characterDao()
            val descArray = context.resources.getStringArray(R.array.weightCargoArrayWithout)
            val descArray1 = context.resources.getStringArray(R.array.weightCargoArrayWith)
            val characterList = listOf(
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_V),
                    0f,
                    10000f,
                    descArray[0],
                    context.getString(R.string.m3),
                    0f,
                    1f,
                    isStrongMeasure = true
                ),
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_K),
                    0f,
                    100f,
                    descArray[1],
                    "",
                    9.1f,
                    1f
                ),
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_Pb),
                    0f,
                    10000f,
                    descArray[2],
                    context.getString(R.string.t),
                    0f,
                    1f,
                    false
                ),

                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_V1),
                    0f,
                    10000f,
                    descArray1[0],
                    context.getString(R.string.m3),
                    0f,
                    1f,
                    isStrongMeasure = true
                ),
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_V2),
                    0f,
                    10000f,
                    descArray1[1],
                    context.getString(R.string.m3),
                    0f,
                    1f,
                    isStrongMeasure = true
                ),
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_Kp),
                    0f,
                    10000f,
                    descArray1[2],
                    "",
                    1.3f,
                    1f
                ),
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_k1),
                    0f,
                    10000f,
                    descArray1[3],
                    "",
                    7f,
                    1f
                ),
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_k2),
                    0f,
                    10000f,
                    descArray1[4],
                    "",
                    3f,
                    1f
                ),
                Character(
                    null,
                    mapTypes[context.getString(R.string.type01)]!!.id!!,
                    context.getString(R.string.Weight_Pc),
                    0f,
                    10000f,
                    descArray1[5],
                    context.getString(R.string.t),
                    0f,
                    1f,
                    false
                ),
            )
            characterDao?.insertSome(characterList)
        }
    }
}
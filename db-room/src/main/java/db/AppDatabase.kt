package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import data.CalcSaveRepository
import data.model.CalcInfo
import data.model.CalcSave
import data.model.TypeEnum
import data.model.calcNamesEnum.CargoWeightNameEnum
import data.model.container.CargoWeightContainer
import db.dao.CharacterDao
import db.dao.PossibleValueDao
import db.dao.SaveDao
import db.dao.TypeDao
import db.dao.ValueDao
import db.entities.Character
import db.entities.PossibleValue
import db.entities.Save
import db.entities.Type
import db.entities.Value
import db.mapper.CalcTypeMapper
import domain.model.volume.VolumeUnit
import domain.model.weight.WeightUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [Save::class, Type::class,
    Character::class, Value::class, PossibleValue::class],
    version = 11)
abstract class AppDatabase : RoomDatabase(), CalcSaveRepository {

    abstract fun saveDao(): SaveDao?
    abstract fun typeDao(): TypeDao?
    abstract fun characterDao(): CharacterDao?
    abstract fun valueDao(): ValueDao?
    abstract fun possibleValueDao(): PossibleValueDao?
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

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
                    instance?.setDefaultValues()
                }
            }
            return instance!!
        }
    }

    override suspend fun getAllCalcSaves(): List<CalcSave> = withContext(Dispatchers.IO){
        val typeDao = typeDao()!!
        val saveDao = saveDao()!!
        val valueDao = valueDao()!!
        val characterDao = characterDao()!!
        val saves = saveDao.all

        val calcSaveList = mutableListOf<CalcSave>()

        saves.forEach { it ->
            val type = typeDao.getById(it.typeIdFk)

            val characters = characterDao.getAllByTypeIdFk(type.id!!)
            val vB = characters.find { character -> character.name == CargoWeightNameEnum.Vb.name }
            val v1C = characters.find { character -> character.name == CargoWeightNameEnum.V1c.name }
            val v2C = characters.find { character -> character.name == CargoWeightNameEnum.V2c.name }
            val mB = characters.find { character -> character.name == CargoWeightNameEnum.Mb.name }
            val mC = characters.find { character -> character.name == CargoWeightNameEnum.Mc.name }

            val vBValue = valueDao.getByCharacterIdFkAndSaveIdFk(vB!!.id!!, it.id!!)
            val v1CValue = valueDao.getByCharacterIdFkAndSaveIdFk(v1C!!.id!!, it.id!!)
            val v2CValue = valueDao.getByCharacterIdFkAndSaveIdFk(v2C!!.id!!, it.id!!)
            val mBValue = valueDao.getByCharacterIdFkAndSaveIdFk(mB!!.id!!, it.id!!)
            val mCValue = valueDao.getByCharacterIdFkAndSaveIdFk(mC!!.id!!, it.id!!)
            if (type.name == TypeEnum.WeightOfCargo.name){
                val container = CargoWeightContainer(
                    vB = VolumeUnit(CalcTypeMapper.getVolume(vBValue.measuredIn), vBValue.value),
                    v1C = VolumeUnit(CalcTypeMapper.getVolume(v1CValue.measuredIn), v1CValue.value),
                    v2C = VolumeUnit(CalcTypeMapper.getVolume(v2CValue.measuredIn), v2CValue.value),
                    mB = WeightUnit(CalcTypeMapper.getWeight(mBValue.measuredIn), mBValue.value),
                    mC = WeightUnit(CalcTypeMapper.getWeight(mCValue.measuredIn), mCValue.value),
                )
                calcSaveList.add(
                    CalcSave(
                        type = CalcTypeMapper.toCalcType(type.name)!!,
                        name = it.name,
                        description = it.description,
                        date = it.date,
                        container = container
                    )
                )
            }
        }

        return@withContext calcSaveList
    }

    override suspend fun getAllCalcInfo(): List<CalcInfo> = withContext(Dispatchers.IO){
        val typeDao = typeDao()!!
        val saveDao = saveDao()!!
        val saves = saveDao.all

        val listCalcInfo = mutableListOf<CalcInfo>()

        saves.forEach {
            val type = typeDao.getById(it.typeIdFk)
            listCalcInfo.add(
                CalcInfo(
                    type = CalcTypeMapper.toCalcType(type.name)!!,
                    name = it.name,
                    description = it.description,
                    date = it.date,
                    result = it.result
                )
            )
        }

        return@withContext listCalcInfo
    }

    override suspend fun setCalcSave(calcSave: CalcSave): Boolean = withContext(Dispatchers.IO){
        when(calcSave.container){
            is CargoWeightContainer -> {
                val container = (calcSave.container as CargoWeightContainer)
                val characterDao = characterDao()!!
                val characters = characterDao.getAllByTypeIdFk(typeDao()!!.getByName(calcSave.type.typeEnum.name).id!!)
                val vB = characters.find { it.name == CargoWeightNameEnum.Vb.name }
                val v1C = characters.find { it.name == CargoWeightNameEnum.V1c.name }
                val v2C = characters.find { it.name == CargoWeightNameEnum.V2c.name }
                val mB = characters.find { it.name == CargoWeightNameEnum.Mb.name }
                val mC = characters.find { it.name == CargoWeightNameEnum.Mc.name }

                val saveId = saveDao()!!.insert(Save(
                    name = calcSave.name,
                    description = calcSave.description,
                    typeIdFk = typeDao()!!.getByName(calcSave.type.typeEnum.name).id!!,
                    result = container.getResult()
                ))

                val valueDao = valueDao()!!
                valueDao.insertSome(
                    listOf(
                        Value(
                            characterIdFk = vB!!.id!!,
                            saveIdFk = saveId,
                            value = container.vB.value,
                            measuredIn = container.vB.measuredIn.name
                        ),
                        Value(
                            characterIdFk = v1C!!.id!!,
                            saveIdFk = saveId,
                            value = container.v1C.value,
                            measuredIn = container.v1C.measuredIn.name
                        ),
                        Value(
                            characterIdFk = v2C!!.id!!,
                            saveIdFk = saveId,
                            value = container.v2C.value,
                            measuredIn = container.v2C.measuredIn.name
                        ),
                        Value(
                            characterIdFk = mB!!.id!!,
                            saveIdFk = saveId,
                            value = container.mB.value,
                            measuredIn = container.mB.measuredIn.name
                        ),
                        Value(
                            characterIdFk = mC!!.id!!,
                            saveIdFk = saveId,
                            value = container.mC.value,
                            measuredIn = container.mC.measuredIn.name
                        ),
                    )
                )
                return@withContext true
            }
        }
        return@withContext false
    }

    private suspend fun setDefaultValues() = withContext(Dispatchers.IO){

        if (typeDao()!!.all.isEmpty()) {
            val typeDao = typeDao()
            val typeId = typeDao!!.insert(Type(name = TypeEnum.WeightOfCargo.name))
            val characterDao = characterDao()
            characterDao!!.insertSome(
                listOf(
                    Character(
                        typeIdFk = typeId,
                        name = CargoWeightNameEnum.Vb.name,
                        isInput = true,
                        isStrongMeasure = true
                    ),
                    Character(
                        typeIdFk = typeId,
                        name = CargoWeightNameEnum.V1c.name,
                        isInput = true,
                        isStrongMeasure = true
                    ),
                    Character(
                        typeIdFk = typeId,
                        name = CargoWeightNameEnum.V2c.name,
                        isInput = true,
                        isStrongMeasure = true
                    ),
                    Character(
                        typeIdFk = typeId,
                        name = CargoWeightNameEnum.Mb.name,
                        isInput = false,
                        isStrongMeasure = false
                    ),
                    Character(
                        typeIdFk = typeId,
                        name = CargoWeightNameEnum.Mc.name,
                        isInput = false,
                        isStrongMeasure = false
                    ),
                )
            )
        }

//        val typeDao = typeDao()
//        if (typeDao?.all?.size == 0) {
//            val typesArray = context.resources.getStringArray(R.array.types)
//            val types = ArrayList<Type>()
//            for (item in typesArray) {
//                types.add(Type(null, item))
//            }
//            typeDao.insertSome(types)
//            val types1 = typeDao.all
//            for (item in types1) {
//                mapTypes[item.name] = item
//            }
//
//            val characterDao = characterDao()
//            val descArray = context.resources.getStringArray(R.array.weightCargoArrayWithout)
//            val descArray1 = context.resources.getStringArray(R.array.weightCargoArrayWith)
//            val characterList = listOf(
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_V),
//                    0f,
//                    10000f,
//                    descArray[0],
//                    context.getString(R.string.m3),
//                    0f,
//                    1f,
//                    isStrongMeasure = true
//                ),
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_K),
//                    0f,
//                    100f,
//                    descArray[1],
//                    "",
//                    9.1f,
//                    1f
//                ),
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_Pb),
//                    0f,
//                    10000f,
//                    descArray[2],
//                    context.getString(R.string.t),
//                    0f,
//                    1f,
//                    false
//                ),
//
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_V1),
//                    0f,
//                    10000f,
//                    descArray1[0],
//                    context.getString(R.string.m3),
//                    0f,
//                    1f,
//                    isStrongMeasure = true
//                ),
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_V2),
//                    0f,
//                    10000f,
//                    descArray1[1],
//                    context.getString(R.string.m3),
//                    0f,
//                    1f,
//                    isStrongMeasure = true
//                ),
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_Kp),
//                    0f,
//                    10000f,
//                    descArray1[2],
//                    "",
//                    1.3f,
//                    1f
//                ),
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_k1),
//                    0f,
//                    10000f,
//                    descArray1[3],
//                    "",
//                    7f,
//                    1f
//                ),
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_k2),
//                    0f,
//                    10000f,
//                    descArray1[4],
//                    "",
//                    3f,
//                    1f
//                ),
//                Character(
//                    null,
//                    mapTypes[context.getString(R.string.type01)]!!.id!!,
//                    context.getString(R.string.Weight_Pc),
//                    0f,
//                    10000f,
//                    descArray1[5],
//                    context.getString(R.string.t),
//                    0f,
//                    1f,
//                    false
//                ),
//            )
//            characterDao?.insertSome(characterList)
//        }
    }
}
package db

import GlobalParameter
import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import data.CalcSaveRepository
import data.model.CalcInfo
import data.model.CalcSave
import data.model.SimpleCalcInfo
import data.model.TypeEnum
import data.model.calcNamesEnum.CargoWeightNameEnum
import data.model.calcNamesEnum.TempLikvidusNameEnum
import data.model.container.CargoWeightContainer
import data.model.container.TempLikvidusFasonContainer
import data.model.container.TempLikvidusIngotContainer
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
    version = 12)
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

            when (type.name){
                TypeEnum.WeightOfCargo.name ->
                {
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
                TypeEnum.TemperatureFason.name ->
                {
                    val w = characters.find { character -> character.name == TempLikvidusNameEnum.W.name }!!
                    val cr = characters.find { character -> character.name == TempLikvidusNameEnum.Cr.name }!!
                    val co = characters.find { character -> character.name == TempLikvidusNameEnum.Co.name }!!
                    val mo = characters.find { character -> character.name == TempLikvidusNameEnum.Mo.name }!!
                    val v = characters.find { character -> character.name == TempLikvidusNameEnum.V.name }!!
                    val al = characters.find { character -> character.name == TempLikvidusNameEnum.Al.name }!!
                    val ni = characters.find { character -> character.name == TempLikvidusNameEnum.Ni.name }!!
                    val mn = characters.find { character -> character.name == TempLikvidusNameEnum.Mn.name }!!
                    val cu = characters.find { character -> character.name == TempLikvidusNameEnum.Cu.name }!!
                    val si = characters.find { character -> character.name == TempLikvidusNameEnum.Si.name }!!
                    val ti = characters.find { character -> character.name == TempLikvidusNameEnum.Ti.name }!!
                    val s = characters.find { character -> character.name == TempLikvidusNameEnum.S.name }!!
                    val p = characters.find { character -> character.name == TempLikvidusNameEnum.P.name }!!
                    val c = characters.find { character -> character.name == TempLikvidusNameEnum.C.name }!!
                    val resTemp = characters.find { character -> character.name == TempLikvidusNameEnum.ResTemp.name }!!

                    val wValue = valueDao.getByCharacterIdFkAndSaveIdFk(w.id!!, it.id!!)
                    val crValue = valueDao.getByCharacterIdFkAndSaveIdFk(cr.id!!, it.id!!)
                    val coValue = valueDao.getByCharacterIdFkAndSaveIdFk(co.id!!, it.id!!)
                    val moValue = valueDao.getByCharacterIdFkAndSaveIdFk(mo.id!!, it.id!!)
                    val vValue = valueDao.getByCharacterIdFkAndSaveIdFk(v.id!!, it.id!!)
                    val alValue = valueDao.getByCharacterIdFkAndSaveIdFk(al.id!!, it.id!!)
                    val niValue = valueDao.getByCharacterIdFkAndSaveIdFk(ni.id!!, it.id!!)
                    val mnValue = valueDao.getByCharacterIdFkAndSaveIdFk(mn.id!!, it.id!!)
                    val cuValue = valueDao.getByCharacterIdFkAndSaveIdFk(cu.id!!, it.id!!)
                    val siValue = valueDao.getByCharacterIdFkAndSaveIdFk(si.id!!, it.id!!)
                    val tiValue = valueDao.getByCharacterIdFkAndSaveIdFk(ti.id!!, it.id!!)
                    val sValue = valueDao.getByCharacterIdFkAndSaveIdFk(s.id!!, it.id!!)
                    val pValue = valueDao.getByCharacterIdFkAndSaveIdFk(p.id!!, it.id!!)
                    val cValue = valueDao.getByCharacterIdFkAndSaveIdFk(c.id!!, it.id!!)
                    val resTempValue = valueDao.getByCharacterIdFkAndSaveIdFk(resTemp.id!!, it.id!!)

                    val container = TempLikvidusFasonContainer(
                        w = wValue.value,
                        cr = crValue.value,
                        co = coValue.value,
                        mo = moValue.value,
                        v = vValue.value,
                        al = alValue.value,
                        ni = niValue.value,
                        mn = mnValue.value,
                        cu = cuValue.value,
                        si = siValue.value,
                        ti = tiValue.value,
                        s = sValue.value,
                        p = pValue.value,
                        c = cValue.value,
                        res = resTempValue.value
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
                TypeEnum.TemperatureIngot.name ->
                {
                    val w = characters.find { character -> character.name == TempLikvidusNameEnum.W.name }!!
                    val cr = characters.find { character -> character.name == TempLikvidusNameEnum.Cr.name }!!
                    val co = characters.find { character -> character.name == TempLikvidusNameEnum.Co.name }!!
                    val mo = characters.find { character -> character.name == TempLikvidusNameEnum.Mo.name }!!
                    val v = characters.find { character -> character.name == TempLikvidusNameEnum.V.name }!!
                    val al = characters.find { character -> character.name == TempLikvidusNameEnum.Al.name }!!
                    val ni = characters.find { character -> character.name == TempLikvidusNameEnum.Ni.name }!!
                    val mn = characters.find { character -> character.name == TempLikvidusNameEnum.Mn.name }!!
                    val cu = characters.find { character -> character.name == TempLikvidusNameEnum.Cu.name }!!
                    val si = characters.find { character -> character.name == TempLikvidusNameEnum.Si.name }!!
                    val ti = characters.find { character -> character.name == TempLikvidusNameEnum.Ti.name }!!
                    val s = characters.find { character -> character.name == TempLikvidusNameEnum.S.name }!!
                    val p = characters.find { character -> character.name == TempLikvidusNameEnum.P.name }!!
                    val c = characters.find { character -> character.name == TempLikvidusNameEnum.C.name }!!
                    val resTemp = characters.find { character -> character.name == TempLikvidusNameEnum.ResTemp.name }!!

                    val wValue = valueDao.getByCharacterIdFkAndSaveIdFk(w.id!!, it.id!!)
                    val crValue = valueDao.getByCharacterIdFkAndSaveIdFk(cr.id!!, it.id!!)
                    val coValue = valueDao.getByCharacterIdFkAndSaveIdFk(co.id!!, it.id!!)
                    val moValue = valueDao.getByCharacterIdFkAndSaveIdFk(mo.id!!, it.id!!)
                    val vValue = valueDao.getByCharacterIdFkAndSaveIdFk(v.id!!, it.id!!)
                    val alValue = valueDao.getByCharacterIdFkAndSaveIdFk(al.id!!, it.id!!)
                    val niValue = valueDao.getByCharacterIdFkAndSaveIdFk(ni.id!!, it.id!!)
                    val mnValue = valueDao.getByCharacterIdFkAndSaveIdFk(mn.id!!, it.id!!)
                    val cuValue = valueDao.getByCharacterIdFkAndSaveIdFk(cu.id!!, it.id!!)
                    val siValue = valueDao.getByCharacterIdFkAndSaveIdFk(si.id!!, it.id!!)
                    val tiValue = valueDao.getByCharacterIdFkAndSaveIdFk(ti.id!!, it.id!!)
                    val sValue = valueDao.getByCharacterIdFkAndSaveIdFk(s.id!!, it.id!!)
                    val pValue = valueDao.getByCharacterIdFkAndSaveIdFk(p.id!!, it.id!!)
                    val cValue = valueDao.getByCharacterIdFkAndSaveIdFk(c.id!!, it.id!!)
                    val resTempValue = valueDao.getByCharacterIdFkAndSaveIdFk(resTemp.id!!, it.id!!)

                    val container = TempLikvidusIngotContainer(
                        w = wValue.value,
                        cr = crValue.value,
                        co = coValue.value,
                        mo = moValue.value,
                        v = vValue.value,
                        al = alValue.value,
                        ni = niValue.value,
                        mn = mnValue.value,
                        cu = cuValue.value,
                        si = siValue.value,
                        ti = tiValue.value,
                        s = sValue.value,
                        p = pValue.value,
                        c = cValue.value,
                        res = resTempValue.value
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
            is CargoWeightContainer ->
            {
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
                GlobalParameter.setSavesChanged()
                return@withContext true
            }

            is TempLikvidusFasonContainer ->
            {
                val container = (calcSave.container as TempLikvidusFasonContainer)
                val characterDao = characterDao()!!
                val characters = characterDao.getAllByTypeIdFk(typeDao()!!.getByName(calcSave.type.typeEnum.name).id!!)

                val saveId = saveDao()!!.insert(Save(
                    name = calcSave.name,
                    description = calcSave.description,
                    typeIdFk = typeDao()!!.getByName(calcSave.type.typeEnum.name).id!!,
                    result = container.getResult()
                ))

                val valueDao = valueDao()!!

                for (tempLikvidusNameEnum in TempLikvidusNameEnum.values()){
                    val character = characters.find { it.name == tempLikvidusNameEnum.name }!!

                    var value = 0f
                    when (character.name){
                        TempLikvidusNameEnum.W.name -> value = container.w
                        TempLikvidusNameEnum.Cr.name -> value = container.cr
                        TempLikvidusNameEnum.Co.name -> value = container.co
                        TempLikvidusNameEnum.Mo.name -> value = container.mo
                        TempLikvidusNameEnum.V.name -> value = container.v
                        TempLikvidusNameEnum.Al.name -> value = container.al
                        TempLikvidusNameEnum.Ni.name -> value = container.ni
                        TempLikvidusNameEnum.Mn.name -> value = container.mn
                        TempLikvidusNameEnum.Cu.name -> value = container.cu
                        TempLikvidusNameEnum.Si.name -> value = container.si
                        TempLikvidusNameEnum.Ti.name -> value = container.ti
                        TempLikvidusNameEnum.S.name -> value = container.s
                        TempLikvidusNameEnum.P.name -> value = container.p
                        TempLikvidusNameEnum.C.name -> value = container.c
                        TempLikvidusNameEnum.ResTemp.name -> value = container.res
                    }

                    valueDao.insert(Value(
                        characterIdFk = character.id!!,
                        saveIdFk = saveId,
                        value = value
                    ))
                }
                GlobalParameter.setSavesChanged()
                return@withContext true
            }

            is TempLikvidusIngotContainer ->
            {
                val container = (calcSave.container as TempLikvidusIngotContainer)
                val characterDao = characterDao()!!
                val characters = characterDao.getAllByTypeIdFk(typeDao()!!.getByName(calcSave.type.typeEnum.name).id!!)

                val saveId = saveDao()!!.insert(Save(
                    name = calcSave.name,
                    description = calcSave.description,
                    typeIdFk = typeDao()!!.getByName(calcSave.type.typeEnum.name).id!!,
                    result = container.getResult()
                ))

                val valueDao = valueDao()!!

                for (tempLikvidusNameEnum in TempLikvidusNameEnum.values()){
                    val character = characters.find { it.name == tempLikvidusNameEnum.name }!!

                    var value = 0f
                    when (character.name){
                        TempLikvidusNameEnum.W.name -> value = container.w
                        TempLikvidusNameEnum.Cr.name -> value = container.cr
                        TempLikvidusNameEnum.Co.name -> value = container.co
                        TempLikvidusNameEnum.Mo.name -> value = container.mo
                        TempLikvidusNameEnum.V.name -> value = container.v
                        TempLikvidusNameEnum.Al.name -> value = container.al
                        TempLikvidusNameEnum.Ni.name -> value = container.ni
                        TempLikvidusNameEnum.Mn.name -> value = container.mn
                        TempLikvidusNameEnum.Cu.name -> value = container.cu
                        TempLikvidusNameEnum.Si.name -> value = container.si
                        TempLikvidusNameEnum.Ti.name -> value = container.ti
                        TempLikvidusNameEnum.S.name -> value = container.s
                        TempLikvidusNameEnum.P.name -> value = container.p
                        TempLikvidusNameEnum.C.name -> value = container.c
                        TempLikvidusNameEnum.ResTemp.name -> value = container.res
                    }

                    valueDao.insert(Value(
                        characterIdFk = character.id!!,
                        saveIdFk = saveId,
                        value = value
                    ))
                }
                GlobalParameter.setSavesChanged()
                return@withContext true
            }
        }
        return@withContext false
    }

    override suspend fun setCalcSaveDescription(
        simpleCalcInfo: SimpleCalcInfo,
        newDescription: String
    ): Boolean {
        val saveDao = saveDao()!!
        val save = saveDao.getByNameDescriptionDateResult(
            name = simpleCalcInfo.name,
            description = simpleCalcInfo.description,
            date = simpleCalcInfo.date,
            result = simpleCalcInfo.result.replace("\n", " "),
        )
        save.description = newDescription
        saveDao.update(save)
        GlobalParameter.setSavesChanged()
        return true
    }

    override suspend fun setCalcSaveName(simpleCalcInfo: SimpleCalcInfo, newName: String): Boolean {
        val saveDao = saveDao()!!
        val save = saveDao.getByNameDescriptionDateResult(
            name = simpleCalcInfo.name,
            description = simpleCalcInfo.description,
            date = simpleCalcInfo.date,
            result = simpleCalcInfo.result.replace("\n", " "),
        )
        save.name = newName
        saveDao.update(save)
        GlobalParameter.setSavesChanged()
        return true
    }

    override suspend fun deleteCalcSave(simpleCalcInfo: SimpleCalcInfo): Boolean {
        val saveDao = saveDao()!!
        val valueDao = valueDao()!!
        val characterDao = characterDao()!!
        val save = saveDao.getByNameDescriptionDateResult(
            name = simpleCalcInfo.name,
            description = simpleCalcInfo.description,
            date = simpleCalcInfo.date,
            result = simpleCalcInfo.result.replace("\n", " "),
        )
        val characters = characterDao.getAllByTypeIdFk(save.typeIdFk)
        characters.forEach {
            val value = valueDao.getByCharacterIdFkAndSaveIdFk(it.id!!, save.id!!)
            valueDao.delete(value)
        }
        saveDao.delete(save)
        GlobalParameter.setSavesChanged()
        return true
    }

    private suspend fun setDefaultValues() = withContext(Dispatchers.IO){
        val allTypes = typeDao()!!.all
        if (!allTypes.any { it.name == TypeEnum.TemperatureFason.name })
        {
            //температура фасонного литья
            val typeDao = typeDao()!!
            val characterDao = characterDao()!!
            val typeTempLikvidusFasonId = typeDao.insert(Type(name = TypeEnum.TemperatureFason.name))

            for (tempLikvidusNameEnum in TempLikvidusNameEnum.values()){
                val isInput = tempLikvidusNameEnum.name != TempLikvidusNameEnum.ResTemp.name

                characterDao.insert(
                    Character(
                        typeIdFk = typeTempLikvidusFasonId,
                        name = tempLikvidusNameEnum.name,
                        isStrongMeasure = true,
                        isInput = isInput
                ))
            }
        }

        if (!allTypes.any { it.name == TypeEnum.TemperatureIngot.name })
        {
            //температура литья слитков
            val typeDao = typeDao()!!
            val characterDao = characterDao()!!
            val typeTempLikvidusIngotId = typeDao.insert(Type(name = TypeEnum.TemperatureIngot.name))

            for (tempLikvidusNameEnum in TempLikvidusNameEnum.values()){
                val isInput = tempLikvidusNameEnum.name != TempLikvidusNameEnum.ResTemp.name

                characterDao.insert(
                    Character(
                        typeIdFk = typeTempLikvidusIngotId,
                        name = tempLikvidusNameEnum.name,
                        isStrongMeasure = true,
                        isInput = isInput
                    ))
            }
        }

        if (!allTypes.any { it.name == TypeEnum.WeightOfCargo.name }) {
            val typeDao = typeDao()!!
            val typeWeightCargoId = typeDao.insert(Type(name = TypeEnum.WeightOfCargo.name))

            val characterDao = characterDao()
            characterDao!!.insertSome(
                listOf(
                    // вес груза
                    Character(
                        typeIdFk = typeWeightCargoId,
                        name = CargoWeightNameEnum.Vb.name,
                        isInput = true,
                        isStrongMeasure = true
                    ),
                    Character(
                        typeIdFk = typeWeightCargoId,
                        name = CargoWeightNameEnum.V1c.name,
                        isInput = true,
                        isStrongMeasure = true
                    ),
                    Character(
                        typeIdFk = typeWeightCargoId,
                        name = CargoWeightNameEnum.V2c.name,
                        isInput = true,
                        isStrongMeasure = true
                    ),
                    Character(
                        typeIdFk = typeWeightCargoId,
                        name = CargoWeightNameEnum.Mb.name,
                        isInput = false,
                        isStrongMeasure = false
                    ),
                    Character(
                        typeIdFk = typeWeightCargoId,
                        name = CargoWeightNameEnum.Mc.name,
                        isInput = false,
                        isStrongMeasure = false
                    ),
                )
            )
        }
    }
}
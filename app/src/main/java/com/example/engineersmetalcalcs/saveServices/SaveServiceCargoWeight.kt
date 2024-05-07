package com.example.engineersmetalcalcs.saveServices

import android.content.Context
import android.content.res.Resources
import com.example.engineersmetalcalcs.MyGlobalParameters
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.db.entities.Character
import com.example.engineersmetalcalcs.db.entities.Save
import com.example.engineersmetalcalcs.db.entities.Value
import com.example.engineersmetalcalcs.listItem.CalcUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class SaveServiceCargoWeight(
    private val calcUnitsWithout: ArrayList<CalcUnit>, // лист объектов, которые лежат в адаптере и отрисовываются пользователю (коэфициенты не нужны)
    private val calcUnitsWith: ArrayList<CalcUnit>, // лист объектов, которые лежат в адаптере и отрисовываются пользователю (коэфициенты не нужны)
    publicMapOfCalcUnit: MutableMap<String, CalcUnit>, // мапа всех объектов (calcUnits и всяких коэфициентов) для быстрого получения по названию
    val context: Context,
) : BaseSaveService(
    publicMapOfCalcUnit,
    context,
) {
    private val privateMapOfCalcUnit = mutableMapOf<String, CalcUnit>()
    private val privateMapOfCharacter = mutableMapOf<String, Character>()
    // get - берет данные из БД
    override suspend fun get(): Unit = withContext(Dispatchers.IO){
        val save = MyGlobalParameters.getSave()
        calcUnitsWithout.clear()
        calcUnitsWith.clear()

        if(save == null){
            for(i in characters.indices){
                val calcUnit= CalcUnit.fromCharacters(characters[i])
                privateMapOfCalcUnit[characters[i].name] = calcUnit
                publicMapOfCalcUnit[characters[i].name] = calcUnit
                privateMapOfCharacter[characters[i].name] = characters[i]
            }
        }
        else{
            values.clear()
            for(i in characters.indices){
                var value: Value
                var calcUnit: CalcUnit
                if(characters[i].measuredIn.isNotEmpty()){
                    value = valueDao?.getByCharacterIdFkAndSaveIdFk(characters[i].id!!, save.id!!)!!
                    values[characters[i].name] = value
                    calcUnit = CalcUnit.fromCharacterAndValue(characters[i], value)
                }
                else {
                    calcUnit= CalcUnit.fromCharacters(characters[i])
                }
                privateMapOfCalcUnit[characters[i].name] = calcUnit
                publicMapOfCalcUnit[characters[i].name] = calcUnit
                privateMapOfCharacter[characters[i].name] = characters[i]
            }
        }

        calcUnitsWithout.add(privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V)]!!)
        calcUnitsWithout.add(privateMapOfCalcUnit[context.resources.getString(R.string.Weight_Pb)]!!)

        calcUnitsWith.add(privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V1)]!!)
        calcUnitsWith.add(privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V2)]!!)
        calcUnitsWith.add(privateMapOfCalcUnit[context.resources.getString(R.string.Weight_Pc)]!!)
    }

    // set сохраняет данные в БД
    override suspend fun set(name: String, description: String): Boolean = withContext(Dispatchers.IO){
        val insertedSaves = mutableListOf<Value>()
        var value: Value
        var save: Save? = null
        var nameOfSave = name

        try {
            if(nameOfSave.isEmpty())
                nameOfSave = context.resources.getString(R.string.calc_weight_of_the_cargo)
            save = Save(name = nameOfSave, description = description, typeIdFk = typeId)
            val saveId = saveDao?.insert(save)
            save.id = saveId
            value = Value(characterIdFk = privateMapOfCharacter[context.resources.getString(R.string.Weight_V)]!!.id!!,
                saveIdFk = saveId!!, value = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V)]!!.value,
                measuredIn = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V)]!!.measuredIn)
            if (valueDao?.insert(value) != null)
                insertedSaves.add(value)

            value = Value(characterIdFk = privateMapOfCharacter[context.resources.getString(R.string.Weight_Pb)]!!.id!!,
                saveIdFk = saveId, value = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_Pb)]!!.value,
                measuredIn = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_Pb)]!!.measuredIn)
            if (valueDao?.insert(value) != null)
                insertedSaves.add(value)

            value = Value(characterIdFk = privateMapOfCharacter[context.resources.getString(R.string.Weight_V1)]!!.id!!,
                saveIdFk = saveId, value = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V1)]!!.value,
                measuredIn = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V1)]!!.measuredIn)
            if (valueDao?.insert(value) != null)
                insertedSaves.add(value)

            value = Value(characterIdFk = privateMapOfCharacter[context.resources.getString(R.string.Weight_V2)]!!.id!!,
                saveIdFk = saveId, value = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V2)]!!.value,
                measuredIn = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_V2)]!!.measuredIn)
            if (valueDao?.insert(value) != null)
                insertedSaves.add(value)

            value = Value(characterIdFk = privateMapOfCharacter[context.resources.getString(R.string.Weight_Pc)]!!.id!!,
                saveIdFk = saveId, value = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_Pc)]!!.value,
                measuredIn = privateMapOfCalcUnit[context.resources.getString(R.string.Weight_Pc)]!!.measuredIn)
            if (valueDao?.insert(value) != null)
                insertedSaves.add(value)

        } catch (e: Exception){
            for (item in insertedSaves){
                valueDao?.delete(item)
            }
            saveDao?.delete(save!!)
            return@withContext false
        }
        return@withContext true
    }
}
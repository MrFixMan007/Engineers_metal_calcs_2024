package com.example.engineersmetalcalcs.saveServices

import android.content.Context
import com.example.engineersmetalcalcs.MyGlobalParameters
import com.example.engineersmetalcalcs.db.entities.Save
import com.example.engineersmetalcalcs.db.entities.Value
import com.example.engineersmetalcalcs.listItem.CalcUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveServiceCargoWeight(
    calcUnits: ArrayList<CalcUnit>, // лист объектов, которые лежат в адаптере и отрисовываются пользователю (коэфициенты не нужны)
    map: MutableMap<String, CalcUnit>, // мапа всех объектов (calcUnits и всяких коэфициентов) для быстрого получения по названию
    context: Context,
    resource: String // передаём название типа расчёта, по которому будет запрос к БД
) : BaseSaveService(
    calcUnits,
    map,
    context,
    resource
) {
    // get - берет данные из БД
    override suspend fun get() = withContext(Dispatchers.IO){
        val save = MyGlobalParameters.getSave()
        calcUnits.clear()
        if(save == null){
            for (item in characters){
                if(item.measuredIn != "") calcUnits.add(CalcUnit.fromCharacters(item))
            }
            var count = 0
            for (i in characters.indices){
                if(characters[i].measuredIn.isNotEmpty()) {
                    map[characters[i].name] = calcUnits[count++]
                }
                else map[characters[i].name] = CalcUnit.fromCharacters(characters[i])
            }
        }
        else{
            values.clear()
            for (item in characters){
                if(item.measuredIn != "") {
                    val value = valueDao?.getByCharacterIdFkAndSaveIdFk(item.id!!, save.id!!)!!
                    values.add(value)
                    calcUnits.add(CalcUnit.fromCharacterAndValue(item, value))
                }
            }
            var count = 0
            for (i in characters.indices){
                if(characters[i].measuredIn.isNotEmpty()) {
                    map[characters[i].name] = calcUnits[count++]
                }
                else map[characters[i].name] = CalcUnit.fromCharacters(characters[i])
            }
        }
    }

    // set сохраняет данные в БД
    override suspend fun set(name: String, description: String) = withContext(Dispatchers.IO){
        val saveId = saveDao?.insert(Save(name = name, description = description, typeIdFk = typeId))
        var count = 0
        for(i in characters.indices){
            if(characters[i].measuredIn.isNotEmpty()) {
                valueDao?.insert(Value(characterIdFk = characters[i].id!!, saveIdFk = saveId!!, value = calcUnits[count].value, measuredIn = calcUnits[count].measuredIn))
                count++
            }
        }
    }
}
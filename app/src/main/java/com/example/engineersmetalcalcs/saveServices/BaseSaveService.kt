package com.example.engineersmetalcalcs.saveServices

import android.content.Context
import android.content.res.Resources
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.db.AppDatabase
import com.example.engineersmetalcalcs.db.entities.Value
import com.example.engineersmetalcalcs.listItem.CalcUnit

abstract class BaseSaveService(
    protected var publicMapOfCalcUnit: MutableMap<String, CalcUnit>,
    context: Context,
) {
    protected val db = AppDatabase.getInstance(context)
    private val characterDao = db.characterDao()
    private val typeDao = db.typeDao()
    protected val valueDao = db.valueDao()
    protected val saveDao = db.saveDao()
    protected val typeId = typeDao?.getByName(context.resources.getString(R.string.type01))?.id!! // по этому типу будут искаться данные
    protected val characters = characterDao?.getAllByTypeIdFk(typeId)!! // все параметры расчёта
    protected val values = mutableMapOf<String, Value>() // значения параметров, которые были введены или выведены

    abstract suspend fun get()
    abstract suspend fun set(name: String, description: String = ""): Boolean?

}
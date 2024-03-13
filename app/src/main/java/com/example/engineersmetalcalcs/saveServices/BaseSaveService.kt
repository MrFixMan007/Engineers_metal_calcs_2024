package com.example.engineersmetalcalcs.saveServices

import android.content.Context
import com.example.engineersmetalcalcs.db.AppDatabase
import com.example.engineersmetalcalcs.db.entities.Value
import com.example.engineersmetalcalcs.listItem.CalcUnit

abstract class BaseSaveService(
    protected var calcUnits: ArrayList<CalcUnit>,
    protected var map: MutableMap<String, CalcUnit>,
    context: Context,
    resource: String
) {
    protected val db = AppDatabase.getInstance(context)
    private val characterDao = db?.characterDao()
    private val typeDao = db?.typeDao()
    protected val valueDao = db?.valueDao()
    protected val saveDao = db?.saveDao()
    protected val typeId = typeDao?.getByName(resource)?.id!! // по этому типу будут искаться данные
    protected val characters = characterDao?.getAllByTypeIdFk(typeId)!! // все параметры расчёта
    protected val values: ArrayList<Value> = ArrayList() // значения параметров, которые были введены или выведены

    abstract suspend fun get()
    abstract suspend fun set(name: String, description: String = "")

}
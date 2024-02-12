package com.example.engineersmetalcalcs.unitsOfMeasurement

import android.content.Context
import com.example.engineersmetalcalcs.R

class CoefArrayMapper(val context: Context){
private val coefficients = mapOf(
    context.getString(R.string.mm) to  1,
    context.getString(R.string.cm) to  10,
    context.getString(R.string.dm) to  100,
    context.getString(R.string.m) to 1000,
    context.getString(R.string.km) to  1000000,

    context.getString(R.string.g) to 1,
    context.getString(R.string.kg) to 1000,
    context.getString(R.string.c) to 100000,
    context.getString(R.string.t) to 1000000,

    context.getString(R.string.mm3) to 1,
    context.getString(R.string.cm3) to 1000,
    context.getString(R.string.dm3) to 1000000,
    context.getString(R.string.m3) to 1000000000,

    "" to 1
)

    fun getCoef(input: String): Int{
        return coefficients[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
}
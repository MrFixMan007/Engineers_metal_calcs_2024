package com.example.engineersmetalcalcs.unitsOfMeasurement

import android.content.Context
import com.example.engineersmetalcalcs.R

class CoefArrayMapper(val context: Context){
    private val coefficients = mapOf(
        context.getString(R.string.mm) to  1.0f,
        context.getString(R.string.cm) to  .1f,
        context.getString(R.string.dm) to  .01f,
        context.getString(R.string.m) to .001f,
        context.getString(R.string.km) to  .000001f,

        context.getString(R.string.g) to 1.0f,
        context.getString(R.string.kg) to .001f,
        context.getString(R.string.c) to .00001f,
        context.getString(R.string.t) to .000001f

//        context.getString(R.string.mm3) to 0,
//        context.getString(R.string.cm3) to 1,
//        context.getString(R.string.dm3) to 2,
//        context.getString(R.string.m3) to 3
    )
    fun getCoef(input: String): Float{
        return coefficients[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
}
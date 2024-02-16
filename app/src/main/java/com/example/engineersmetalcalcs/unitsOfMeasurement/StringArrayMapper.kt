package com.example.engineersmetalcalcs.unitsOfMeasurement

import android.content.Context
import com.example.engineersmetalcalcs.R

class StringArrayMapper(val context: Context) {
    private val stringArrayMap = mapOf(
        context.getString(R.string.mm) to R.array.unitsOfLength,
        context.getString(R.string.cm) to R.array.unitsOfLength,
        context.getString(R.string.dm) to R.array.unitsOfLength,
        context.getString(R.string.m) to R.array.unitsOfLength,
        context.getString(R.string.km) to R.array.unitsOfLength,

//        context.getString(R.string.g) to R.array.unitsOfWeight,
        context.getString(R.string.kg) to R.array.unitsOfWeight,
//        context.getString(R.string.c) to R.array.unitsOfWeight,
        context.getString(R.string.t) to R.array.unitsOfWeight,

        context.getString(R.string.mm3) to R.array.unitsOfVolume,
        context.getString(R.string.cm3) to R.array.unitsOfVolume,
        context.getString(R.string.dm3) to R.array.unitsOfVolume,
        context.getString(R.string.m3) to R.array.unitsOfVolume
    )

    fun getStringArrayResourceId(input: String): Int {
        return stringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
}
package com.example.engineersmetalcalcs.calcServices

import android.content.Context
import com.example.engineersmetalcalcs.unitsOfMeasurement.CoefArrayMapper

abstract class BaseCalcService(context: Context) {
    protected val mapper = CoefArrayMapper(context)
    abstract fun calculate()
}
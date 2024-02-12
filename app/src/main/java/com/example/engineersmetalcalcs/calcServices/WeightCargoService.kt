package com.example.engineersmetalcalcs.calcServices

import android.content.Context
import com.example.engineersmetalcalcs.listItem.CalcUnit
import com.example.engineersmetalcalcs.unitsOfMeasurement.CoefArrayMapper
import kotlin.math.roundToInt

data class WeightCargoService(val context: Context, var v: CalcUnit, var k: CalcUnit,
                              var v1: CalcUnit, var v2: CalcUnit, var kP: CalcUnit,
                              var k1: CalcUnit, var k2: CalcUnit, var pB: CalcUnit,
                              var pC: CalcUnit, var roundPB: Int = 2, var roundPC: Int = 2)
{
    fun calculate(){
        val mapper = CoefArrayMapper(context)

        var n1 = v.value * mapper.getCoef(v.measuredIn) * k.value

        pB.value = n1 / mapper.getCoef(pB.measuredIn) / 1000

        n1 = (((k1.value * v1.value * mapper.getCoef(v1.measuredIn)) + (k2.value * v2.value * mapper.getCoef(v2.measuredIn))) * kP.value)

        pC.value = n1 / mapper.getCoef(pC.measuredIn) / 1000
        round()
    }

    private fun round(){
        var r = 1
        var count = roundPB
        while (count > 0) {
            r *= 10
            count--
        }
        pB.value = (pB.value * r).roundToInt().toFloat() / r

        r = 1
        count = roundPC
        while (count > 0) {
            r *= 10
            count--
        }
        pC.value = (pC.value * r).roundToInt().toFloat() / r
    }
}
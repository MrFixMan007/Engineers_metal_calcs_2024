package com.example.engineersmetalcalcs

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.engineersmetalcalcs.calcServices.WeightCargoService
import com.example.engineersmetalcalcs.listItem.CalcUnit
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCargoWeight {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    var v = CalcUnit("", 0.1f, appContext.getString(R.string.m3))
    var vv = CalcUnit("", 100f, appContext.getString(R.string.dm3))
    var k = CalcUnit("", 9.1f)
    var pB = CalcUnit("", 0f, appContext.getString(R.string.t))
    var pB1 = CalcUnit("", 0f, appContext.getString(R.string.kg))

    var v1 = CalcUnit("", 0.07f, appContext.getString(R.string.m3))
    var v2 = CalcUnit("", 0.00025f, appContext.getString(R.string.m3))
    var kP = CalcUnit("", 1.3f)
    var k1 = CalcUnit("", 7f)
    var k2 = CalcUnit("", 5f)
    var pC = CalcUnit("", 0f, appContext.getString(R.string.t))
    var pC1 = CalcUnit("", 0f, appContext.getString(R.string.kg))

    @Test
    fun calc() {
        val weightCalc = WeightCargoService(appContext, v, k, v1, v2, kP, k1, k2, pB, pC)
        weightCalc.calculate()
        assertEquals(0.91f, weightCalc.pB.value)
        assertEquals(0.64f, weightCalc.pC.value)
    }

    @Test
    fun calc1() {
        val weightCalc = WeightCargoService(appContext, v, k, v1, v2, kP, k1, k2, pB, pC, 0, 0)
        weightCalc.calculate()
        assertEquals(1f, weightCalc.pB.value)
        assertEquals(1f, weightCalc.pC.value)
    }

    @Test
    fun calc2() {
        val weightCalc = WeightCargoService(appContext, v, k, v1, v2, kP, k1, k2, pB, pC, 0, 3)
        weightCalc.calculate()
        assertEquals(1f, weightCalc.pB.value)
        assertEquals(0.639f, weightCalc.pC.value)
    }

    @Test
    fun calc3() {
        val weightCalc = WeightCargoService(appContext, v, k, v1, v2, kP, k1, k2, pB, pC, 3, 0)
        weightCalc.calculate()
        assertEquals(0.91f, weightCalc.pB.value)
        assertEquals(1f, weightCalc.pC.value)
    }

    @Test
    fun calc4() {
        val weightCalc = WeightCargoService(appContext, v, k, v1, v2, kP, k1, k2, pB1, pC, 3, 0)
        weightCalc.calculate()
        assertEquals(910f, weightCalc.pB.value)
        assertEquals(1f, weightCalc.pC.value)
    }

    @Test
    fun calc5() {
        val weightCalc = WeightCargoService(appContext, v, k, v1, v2, kP, k1, k2, pB1, pC1, 3, 1)
        weightCalc.calculate()
        assertEquals(910f, weightCalc.pB.value)
        assertEquals(638.6f, weightCalc.pC.value)
    }

    @Test
    fun calc6() {
        val weightCalc = WeightCargoService(appContext, vv, k, v1, v2, kP, k1, k2, pB, pC, 3, 2)
        weightCalc.calculate()
        assertEquals(0.91f, weightCalc.pB.value)
        assertEquals(0.64f, weightCalc.pC.value)
    }
}
package calculation

import domain.usecase.temperatureIngot.inputParam.TemperatureIngotInputParam
import domain.usecase.temperatureIngot.outputParam.TemperatureIngotOutputParam
import org.junit.Assert.assertEquals
import org.junit.Test

class CalcTemperatureIngotTest{
    private val temperatureIngot = CalcTemperatureIngot()

    @Test
    fun `must return 1502,075`(){
        val param = TemperatureIngotInputParam(
            c = 0.3f,
            si = 0.4f,
            mn = 0.6f,
            p = 0.04f,
            s = 0.045f
        )
        val outputParam = TemperatureIngotOutputParam(
            res= 1502.075f,
            resLower = 1602.075f,
            resUpper = 1622.075f,
            resLowerInFurnace = 1642.075f,
            resUpperInFurnace = 1662.075f)
        assertEquals(outputParam, round(temperatureIngot.invoke(param), 3))
    }

    @Test
    fun `must return 1503,275`(){
        val param = TemperatureIngotInputParam(
            c = 0.3f,
            si = 0.4f,
            mn = 0.6f,
            p = 0.01f,
            s = 0.045f
        )
        val outputParam = TemperatureIngotOutputParam(
            res = 1503.275f,
            resLower = 1603.275f,
            resUpper = 1623.275f,
            resLowerInFurnace = 1643.275f,
            resUpperInFurnace = 1663.275f)
        assertEquals(outputParam, round(temperatureIngot.invoke(param), 3))
    }

    @Test
    fun `must return 1492,94`(){
        val param = TemperatureIngotInputParam(
            c = 0.3f,
            si = 0.4f,
            mn = 0.6f,
            p = 0.04f,
            s = 0.045f,
            cr = 0.35f,
            ni = 0.11f,
            cu = 0.15f,
            al = 0.32f,
            ti = 0.2f,
            v = 0.4f,
            mo = 0.62f,
            w = 0.42f,
        )
        val outputParam = TemperatureIngotOutputParam(
            res = 1492.94f,
            resLower = 1592.94f,
            resUpper = 1612.94f,
            resLowerInFurnace = 1632.94f,
            resUpperInFurnace = 1652.94f)
        assertEquals(outputParam, round(temperatureIngot.invoke(param), 2))
    }

    private fun round(param: TemperatureIngotOutputParam, count: Int) : TemperatureIngotOutputParam{
        return TemperatureIngotOutputParam(
            res = Round.round(param.res, count),
            resLower = Round.round(param.resLower, count),
            resUpper = Round.round(param.resUpper, count),
            resLowerInFurnace = Round.round(param.resLowerInFurnace, count),
            resUpperInFurnace = Round.round(param.resUpperInFurnace, count),
        )
    }
}
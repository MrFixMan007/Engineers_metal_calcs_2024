package calculation

import domain.usecase.temperatureFason.inputParam.TemperatureFasonInputParam
import domain.usecase.temperatureFason.outputParam.TemperatureFasonOutputParam
import org.junit.Assert.assertEquals
import org.junit.Test

class CalcTemperatureFasonTest{
    private val temperatureFason = CalcTemperatureFason()

    @Test
    fun `must return 1502,075`(){
        val param = TemperatureFasonInputParam(
            c = 0.3f,
            si = 0.4f,
            mn = 0.6f,
            p = 0.04f,
            s = 0.045f
        )
        val outputParam = TemperatureFasonOutputParam(
            res = 1502.075f,
            resLower = 1552.075f,
            resUpper = 1572.075f,
            resLowerInFurnace = 1592.075f,
            resUpperInFurnace = 1612.075f)
        assertEquals(outputParam, round(temperatureFason.invoke(param), 3))
    }

    @Test
    fun `must return 1503,275`(){
        val param = TemperatureFasonInputParam(
            c = 0.3f,
            si = 0.4f,
            mn = 0.6f,
            p = 0.01f,
            s = 0.045f
        )
        val outputParam = TemperatureFasonOutputParam(
            res = 1503.275f,
            resLower = 1553.275f,
            resUpper = 1573.275f,
            resLowerInFurnace = 1593.275f,
            resUpperInFurnace = 1613.275f)
        assertEquals(outputParam, round(temperatureFason.invoke(param), 3))
    }

    @Test
    fun `must return 1492,94`(){
        val param = TemperatureFasonInputParam(
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
        val outputParam = TemperatureFasonOutputParam(
            res = 1492.94f,
            resLower = 1542.94f,
            resUpper = 1562.94f,
            resLowerInFurnace = 1582.94f,
            resUpperInFurnace = 1602.94f)
        assertEquals(outputParam, round(temperatureFason.invoke(param), 2))
    }

    private fun round(param: TemperatureFasonOutputParam, count: Int) : TemperatureFasonOutputParam {
        return TemperatureFasonOutputParam(
            res = Round.round(param.res, count),
            resLower = Round.round(param.resLower, count),
            resUpper = Round.round(param.resUpper, count),
            resLowerInFurnace = Round.round(param.resLowerInFurnace, count),
            resUpperInFurnace = Round.round(param.resUpperInFurnace, count),
        )
    }
}
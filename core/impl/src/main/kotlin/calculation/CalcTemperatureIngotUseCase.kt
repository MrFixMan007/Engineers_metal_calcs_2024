package calculation

import domain.usecase.temperatureIngot.TemperatureIngotUseCase
import domain.usecase.temperatureIngot.inputParam.TemperatureIngotInputParam
import domain.usecase.temperatureIngot.outputParam.TemperatureIngotOutputParam

class CalcTemperatureIngotUseCase : TemperatureIngotUseCase{
    override fun invoke(param: TemperatureIngotInputParam): TemperatureIngotOutputParam {

        val ti = if (param.ti < 0.02f) 0f else param.ti
        val s = if (param.s < 0.02f) 0f else param.s
        val p = if (param.p < 0.02f) 0f else param.p

        val ni = if (param.ni < 0.1f) 0f else param.ni
        val cu = if (param.cu < 0.1f) 0f else param.cu

        val al = if (param.al < 0.2f) 0f else param.al

        val cr = if (param.cr < 0.3f) 0f else param.cr
        val mo = if (param.mo < 0.3f) 0f else param.mo
        val v = if (param.v < 0.3f) 0f else param.v
        val w = if (param.w < 0.3f) 0f else param.w
        val co = if (param.co < 0.3f) 0f else param.co

        val res = temp - coef_w * w - coef_cr * cr - coef_co * co - coef_mo * mo - coef_v * v - coef_al * al - coef_ni * ni - coef_mn * param.mn - coef_cu * cu - coef_si * param.si - coef_ti * ti - coef_s * s - coef_p * p - coef_c * param.c

        return TemperatureIngotOutputParam(
            res = res,
            resLower = res + coefLower,
            resUpper = res + coefUpper,
            resLowerInFurnace = res + coefLowerInFurnace,
            resUpperInFurnace = res + coefUpperInFurnace)
    }

    companion object{
        private const val temp = 1537f

        private const val coefLower = 100
        private const val coefUpper = 120
        private const val coefLowerInFurnace = 140
        private const val coefUpperInFurnace = 160

        private const val coef_w = 1
        private const val coef_cr = 1.5f
        private const val coef_co = 1.5f
        private const val coef_mo = 2
        private const val coef_v = 2
        private const val coef_al = 3
        private const val coef_ni = 4
        private const val coef_mn = 5
        private const val coef_cu = 5
        private const val coef_si = 8
        private const val coef_ti = 20
        private const val coef_s = 25
        private const val coef_p = 30
        private const val coef_c = 88

        //w - вольфрам
        //cr - хром
        //co - кобальт
        //mo - молибден
        //v - ванадий
        //al - алюминий
        //ni - никель
        //mn - марганец
        //cu - медь
        //si - кремний
        //ti - титан
        //s - сера
        //p - фосфор
        //c - углерод
    }
}
package calculation

import domain.usecase.temperatureFason.outputParam.TemperatureFasonOutputParam
import domain.usecase.temperatureIngot.outputParam.TemperatureIngotOutputParam
import kotlin.math.ceil
import kotlin.math.pow

object Round {
    fun invoke(value: Float, countOfZero: Int = 0): Float{
        if (countOfZero == 0) return ceil(value)
        val scale = 10f.pow(countOfZero)
        return kotlin.math.round(value * scale) / scale
    }

    fun invoke(param: TemperatureFasonOutputParam, countOfZero: Int = 0): TemperatureFasonOutputParam{
        if (countOfZero == 0) return TemperatureFasonOutputParam(
            res = ceil(param.res),
            resLower = ceil(param.resLower),
            resUpper = ceil(param.resUpper),
            resLowerInFurnace = ceil(param.resLowerInFurnace),
            resUpperInFurnace = ceil(param.resUpperInFurnace),
        )

        val scale = 10f.pow(countOfZero)
        return TemperatureFasonOutputParam(
            res = kotlin.math.round(param.res * scale) / scale,
            resLower = kotlin.math.round(param.resLower * scale) / scale,
            resUpper = kotlin.math.round(param.resUpper * scale) / scale,
            resLowerInFurnace = kotlin.math.round(param.resLowerInFurnace * scale) / scale,
            resUpperInFurnace = kotlin.math.round(param.resUpperInFurnace * scale) / scale,
        )
    }

    fun invoke(param: TemperatureIngotOutputParam, countOfZero: Int = 0): TemperatureIngotOutputParam{
        if (countOfZero == 0) return TemperatureIngotOutputParam(
            res = ceil(param.res),
            resLower = ceil(param.resLower),
            resUpper = ceil(param.resUpper),
            resLowerInFurnace = ceil(param.resLowerInFurnace),
            resUpperInFurnace = ceil(param.resUpperInFurnace),
        )

        val scale = 10f.pow(countOfZero)
        return TemperatureIngotOutputParam(
            res = kotlin.math.round(param.res * scale) / scale,
            resLower = kotlin.math.round(param.resLower * scale) / scale,
            resUpper = kotlin.math.round(param.resUpper * scale) / scale,
            resLowerInFurnace = kotlin.math.round(param.resLowerInFurnace * scale) / scale,
            resUpperInFurnace = kotlin.math.round(param.resUpperInFurnace * scale) / scale,
        )
    }

//    private fun round(param: TemperatureFasonOutputParam, count: Int) : TemperatureFasonOutputParam {
//        return TemperatureFasonOutputParam(
//            res = Round.round(param.res, count),
//            resLower = Round.round(param.resLower, count),
//            resUpper = Round.round(param.resUpper, count),
//            resLowerInFurnace = Round.round(param.resLowerInFurnace, count),
//            resUpperInFurnace = Round.round(param.resUpperInFurnace, count),
//        )
//    }
}
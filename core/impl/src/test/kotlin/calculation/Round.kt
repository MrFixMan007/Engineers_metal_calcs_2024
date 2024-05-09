package calculation

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow

object Round {
    fun roundFloor(value: Float, countOfZero: Int) : Float{
        if (countOfZero == 0) return floor(value)
        val scale = 10f.pow(countOfZero)
        return floor(value * scale) / scale
    }

    fun roundCeil(value: Float, countOfZero: Int) : Float{
        if (countOfZero == 0) return ceil(value)
        val scale = 10f.pow(countOfZero)
        return ceil(value * scale) / scale
    }

    fun round(value: Float, countOfZero: Int) : Float{
        if (countOfZero == 0) return ceil(value)
        val scale = 10f.pow(countOfZero)
        return kotlin.math.round(value * scale) / scale
    }
}
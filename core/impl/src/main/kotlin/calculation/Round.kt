package calculation

import kotlin.math.ceil
import kotlin.math.pow

object Round {
    fun invoke(value: Float, countOfZero: Int): Float{
        if (countOfZero == 0) return ceil(value)
        val scale = 10f.pow(countOfZero)
        return kotlin.math.round(value * scale) / scale
    }
}
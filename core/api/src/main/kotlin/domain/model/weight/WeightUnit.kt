package domain.model.weight

data class WeightUnit(override val measuredIn: WeightMeasure,
                      override val value: Float) : Weight {
    private fun coef(): Float {
        return when(measuredIn){
            WeightMeasure.G -> 1f
            WeightMeasure.Kg -> 1000f
            WeightMeasure.C -> 100000f
            WeightMeasure.T -> 1000000f
        }
    }

    override fun g(): Float {
        return value * coef()
    }

    override fun kg(): Float {
        return value * coef() / 1000
    }

    override fun c(): Float {
        return value * coef() / 100000
    }

    override fun t(): Float {
        return value * coef() / 1000000
    }

    companion object{
        fun g(value: Float) : WeightUnit {
            return WeightUnit(WeightMeasure.G, value)
        }
        fun kg(value: Float) : WeightUnit {
            return WeightUnit(WeightMeasure.Kg, value)
        }
        fun c(value: Float) : WeightUnit {
            return WeightUnit(WeightMeasure.C, value)
        }
        fun t(value: Float) : WeightUnit {
            return WeightUnit(WeightMeasure.T, value)
        }
    }
}

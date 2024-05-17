package domain.model.weight

data class WeightUnit(override val measuredIn: WeightMeasure,
                      override val value: Float) : Weight {
    // возвращает граммы
    private fun units(): Float {
        return when(measuredIn){
            WeightMeasure.G -> value * 1
            WeightMeasure.Kg -> value * 1000
            WeightMeasure.C -> value * 100000
            WeightMeasure.T -> value * 1000000
        }
    }

    override fun g(): Float {
        return units()
    }

    override fun kg(): Float {
        return units() / 1000
    }

    override fun c(): Float {
        return units() / 100000
    }

    override fun t(): Float {
        return units() / 1000000
    }

    override fun getUnitsOfMeasure(weightMeasure: WeightMeasure) : Float{
        return when(weightMeasure){
            WeightMeasure.G -> g()
            WeightMeasure.Kg -> kg()
            WeightMeasure.C -> c()
            WeightMeasure.T -> t()
        }
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

package domain.model.length

data class LengthUnit(override val measuredIn: LengthMeasure,
                      override val value: Float) : Length {
    // возвращает мм
    private fun units(): Float {
        return when(measuredIn){
            LengthMeasure.Mm -> value * 1
            LengthMeasure.Cm -> value * 10
            LengthMeasure.Dm -> value * 100
            LengthMeasure.M -> value * 1000
            LengthMeasure.Km -> value * 1000000
        }
    }
    override fun mm(): Float {
        return units()
    }

    override fun cm(): Float {
        return units() / 10
    }

    override fun dm(): Float {
        return units() / 100
    }

    override fun m(): Float {
        return units() / 1000
    }

    override fun km(): Float {
        return units() / 1000000
    }
    override fun getUnitsOfMeasure(lengthMeasure: LengthMeasure) : Float{
        return when(lengthMeasure){
            LengthMeasure.Mm -> mm()
            LengthMeasure.Cm -> cm()
            LengthMeasure.Dm -> dm()
            LengthMeasure.M -> m()
            LengthMeasure.Km -> km()
        }
    }

    companion object{
        fun mm(value: Float) : LengthUnit {
            return LengthUnit(LengthMeasure.M, value)
        }
        fun cm(value: Float) : LengthUnit {
            return LengthUnit(LengthMeasure.Cm, value)
        }
        fun dm(value: Float) : LengthUnit {
            return LengthUnit(LengthMeasure.Dm, value)
        }
        fun m(value: Float) : LengthUnit {
            return LengthUnit(LengthMeasure.M, value)
        }
        fun km(value: Float) : LengthUnit {
            return LengthUnit(LengthMeasure.Km, value)
        }
    }
}

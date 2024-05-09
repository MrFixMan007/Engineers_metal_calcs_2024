package domain.model.volume

data class VolumeUnit (override val measuredIn: VolumeMeasure,
                       override val value: Float) : Volume {
    // возвращает мм3
    private fun coef(): Float {
        return when(measuredIn){
            VolumeMeasure.Mm3 -> 1f
            VolumeMeasure.Cm3 -> 1000f
            VolumeMeasure.Dm3 -> 1000000f
            VolumeMeasure.M3 -> 1000000000f
        }
    }

    override fun mm3(): Float {
        return value * coef()
    }

    override fun cm3(): Float {
        return value * coef() / 1000
    }

    override fun dm3(): Float {
        return value * coef() / 1000000
    }

    override fun m3(): Float {
        return value * coef() / 1000000000
    }

    companion object{
        fun mm3(value: Float) : VolumeUnit {
            return VolumeUnit(VolumeMeasure.Mm3, value)
        }
        fun cm3(value: Float) : VolumeUnit {
            return VolumeUnit(VolumeMeasure.Cm3, value)
        }
        fun dm3(value: Float) : VolumeUnit {
            return VolumeUnit(VolumeMeasure.Dm3, value)
        }
        fun m3(value: Float) : VolumeUnit {
            return VolumeUnit(VolumeMeasure.M3, value)
        }
    }
}
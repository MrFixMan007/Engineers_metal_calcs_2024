package domain.model.volume

data class VolumeUnit (override val measuredIn: VolumeMeasure,
                       override val value: Float) : Volume {
    // возвращает мм3
    private fun units(): Float {
        return when(measuredIn){
            VolumeMeasure.Mm3 -> value * 1
            VolumeMeasure.Cm3 -> value * 1000
            VolumeMeasure.Dm3 -> value * 1000000
            VolumeMeasure.M3 -> value * 1000000000
        }
    }

    override fun mm3(): Float {
        return units()
    }

    override fun cm3(): Float {
        return units() / 1000
    }

    override fun dm3(): Float {
        return units() / 1000000
    }

    override fun m3(): Float {
        return units() / 1000000000
    }
    override fun getUnitsOfMeasure(volumeMeasure: VolumeMeasure) : Float{
        return when(volumeMeasure){
            VolumeMeasure.Mm3 -> mm3()
            VolumeMeasure.Cm3 -> cm3()
            VolumeMeasure.Dm3 -> dm3()
            VolumeMeasure.M3 -> m3()
        }
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
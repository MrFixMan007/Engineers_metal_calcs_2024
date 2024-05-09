package domain.model.volume

interface Volume {
    val measuredIn: VolumeMeasure
    val value: Float
    fun mm3() : Float
    fun cm3() : Float
    fun dm3() : Float
    fun m3 () : Float
}
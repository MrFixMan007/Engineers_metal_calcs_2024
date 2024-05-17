package domain.model.length

interface Length {
    val measuredIn: LengthMeasure
    val value: Float
    fun mm() : Float
    fun cm() : Float
    fun dm() : Float
    fun m () : Float
    fun km () : Float
    fun getUnitsOfMeasure(lengthMeasure: LengthMeasure):Float
}
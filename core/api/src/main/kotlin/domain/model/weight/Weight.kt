package domain.model.weight

interface Weight {
    val measuredIn: WeightMeasure
    val value: Float
    fun g(): Float
    fun kg(): Float
    fun c(): Float
    fun t(): Float
    fun getUnitsOfMeasure(weightMeasure: WeightMeasure):Float
}
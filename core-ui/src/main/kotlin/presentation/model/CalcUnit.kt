package presentation.model

data class CalcUnit(var description: String = "", var value: Float = 0f, var measuredIn: String = "", var type: Int = INPUT){
    companion object {
        const val INPUT = 1
        const val INPUT_COEFICIENT = 2
        const val INPUT_STRONG_MEASURE = 3
        const val OUTPUT = 4
    }
}

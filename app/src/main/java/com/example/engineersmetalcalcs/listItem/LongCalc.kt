package com.example.engineersmetalcalcs.listItem

data class LongCalc(val description: String, var value: Float, val measuredIn: String = "", var type: Int = INPUT){
    companion object{
        const val INPUT = 1
        const val INPUT_COEFICIENT = 2
        const val OUTPUT = 3
    }
}

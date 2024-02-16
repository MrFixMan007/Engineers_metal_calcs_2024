package com.example.engineersmetalcalcs.listItem

import com.example.engineersmetalcalcs.db.entities.Character

data class CalcUnit(var description: String, var value: Float, var measuredIn: String = "", var type: Int = INPUT){
    companion object{
        const val INPUT = 1
        const val INPUT_COEFICIENT = 2
        const val INPUT_STRONG_MEASURE = 3
        const val OUTPUT = 4

        fun fromCharacters(items: List<Character>): List<CalcUnit>{
            val calcList = ArrayList<CalcUnit>()
            for (item in items){
                val calcUnit = fromCharacters(item)
                calcList.add(calcUnit)
            }
            return calcList
        }
        fun fromCharacters(item: Character): CalcUnit{
            val calcUnit: CalcUnit = if(!item.isInput){
                CalcUnit(item.description, item.defaultValue, item.measuredIn, OUTPUT)
            } else if(item.isStrongMeasure){
                CalcUnit(item.description, item.defaultValue, item.measuredIn, INPUT_STRONG_MEASURE)
            } else if(item.measuredIn.isNotEmpty()){
                CalcUnit(item.description, item.defaultValue, item.measuredIn)
            } else{
                CalcUnit(item.description, item.defaultValue, item.measuredIn, INPUT_COEFICIENT)
            }
            return calcUnit
        }
    }
}

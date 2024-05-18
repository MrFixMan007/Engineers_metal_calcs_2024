package data

import data.model.CalcInfo
import data.model.CalcSave

interface CalcSaveRepository {
    suspend fun getAllCalcSaves() : List<CalcSave>
    suspend fun getAllCalcInfo() : List<CalcInfo>
    suspend fun setCalcSave(calcSave: CalcSave) : Boolean
}
package data

import data.model.CalcInfo
import data.model.CalcSave
import data.model.SimpleCalcInfo

interface CalcSaveRepository {
    suspend fun getAllCalcSaves() : List<CalcSave>
    suspend fun getAllCalcInfo() : List<CalcInfo>
    suspend fun setCalcSave(calcSave: CalcSave) : Boolean
    suspend fun setCalcSaveName(simpleCalcInfo: SimpleCalcInfo, newName: String) : Boolean
    suspend fun setCalcSaveDescription(simpleCalcInfo: SimpleCalcInfo, newDescription: String) : Boolean
    suspend fun deleteCalcSave(simpleCalcInfo: SimpleCalcInfo) : Boolean
}
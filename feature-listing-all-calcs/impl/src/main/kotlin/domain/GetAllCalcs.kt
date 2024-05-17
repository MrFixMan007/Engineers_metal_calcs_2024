package domain

import data.model.CalcSave
import data.CalcSaveRepository

class GetAllCalcs {
    fun invoke(calcSaveRepository : CalcSaveRepository) : List<CalcSave>{
        return calcSaveRepository.getAllCalcSaves()
    }
}
package domain

import data.CalcInfo
import data.CalcRepository

class GetAllCalcs {
    fun invoke(calcRepository : CalcRepository) : List<CalcInfo>{
        return calcRepository.getAllCalcs()
    }
}
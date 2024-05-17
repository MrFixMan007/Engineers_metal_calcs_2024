package domain

import data.CalcRepository
import data.CalcType

class GetAllTypes {
    fun invoke(calcRepository : CalcRepository): List<CalcType> {
        return calcRepository.getAllTypes()
    }
}
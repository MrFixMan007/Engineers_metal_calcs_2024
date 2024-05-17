package save

import data.CalcSaveRepository
import data.model.CalcSave
import domain.usecase.saveCalc.SaveCalcUseCase

class SaveCalc(private val calcSaveRepository: CalcSaveRepository) : SaveCalcUseCase {
    override suspend fun invoke(param: CalcSave): Boolean {
        return calcSaveRepository.setCalcSave(param)
    }
}
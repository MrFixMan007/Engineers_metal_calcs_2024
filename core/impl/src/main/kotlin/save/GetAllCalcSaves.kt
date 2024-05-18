package save

import data.CalcSaveRepository
import data.model.CalcSave
import domain.usecase.saveCalc.GetAllCalcSavesUseCase

class GetAllCalcSaves(private val calcSaveRepository: CalcSaveRepository) : GetAllCalcSavesUseCase {
    override suspend fun invoke(): List<CalcSave> {
        return calcSaveRepository.getAllCalcSaves()
    }
}
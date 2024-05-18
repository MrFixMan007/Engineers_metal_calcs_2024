package save

import data.CalcSaveRepository
import data.model.CalcInfo
import domain.usecase.saveCalc.GetAllCalcInfoUseCase

class GetAllCalcInfo(private val calcSaveRepository: CalcSaveRepository) : GetAllCalcInfoUseCase {
    override suspend fun invoke(): List<CalcInfo> {
        return calcSaveRepository.getAllCalcInfo()
    }
}
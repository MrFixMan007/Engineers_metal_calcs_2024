package domain.usecase.saveCalc

import data.model.CalcInfo

interface GetAllCalcInfoUseCase : suspend () -> List<CalcInfo>
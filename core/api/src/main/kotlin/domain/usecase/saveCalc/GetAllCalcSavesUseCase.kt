package domain.usecase.saveCalc

import data.model.CalcSave

interface GetAllCalcSavesUseCase : suspend () -> List<CalcSave>
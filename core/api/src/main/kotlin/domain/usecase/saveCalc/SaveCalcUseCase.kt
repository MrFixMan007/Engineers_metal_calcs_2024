package domain.usecase.saveCalc

import data.model.CalcSave

interface SaveCalcUseCase : suspend (CalcSave) -> Boolean
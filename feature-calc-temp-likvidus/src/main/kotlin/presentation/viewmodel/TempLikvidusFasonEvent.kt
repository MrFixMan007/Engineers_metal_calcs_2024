package presentation.viewmodel

import domain.usecase.temperatureFason.inputParam.TemperatureFasonInputParam

interface TempLikvidusFasonEvent

class SaveCalcTempLikvidFasonEvent(val param: TemperatureFasonInputParam, val name: String, val description: String) : TempLikvidusFasonEvent
class CalcFasonEvent(val param: TemperatureFasonInputParam) : TempLikvidusFasonEvent
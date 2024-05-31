package presentation.viewmodel

import domain.usecase.temperatureIngot.inputParam.TemperatureIngotInputParam

interface TempLikvidusIngotEvent

class SaveCalcTempLikvidIngotEvent(val param: TemperatureIngotInputParam, val name: String, val description: String) : TempLikvidusIngotEvent
class CalcIngotEvent(val param: TemperatureIngotInputParam) : TempLikvidusIngotEvent
package presentation.viewmodel

import domain.usecase.temperatureFason.inputParam.TemperatureFasonInputParam
import domain.usecase.temperatureIngot.inputParam.TemperatureIngotInputParam

interface TempLikvidusEvent

class SaveCalcEvent(val name: String, val description: String) : TempLikvidusEvent
class CalcFasonEvent(val param: TemperatureFasonInputParam) : TempLikvidusEvent
class CalcInputEvent(val param: TemperatureIngotInputParam) : TempLikvidusEvent
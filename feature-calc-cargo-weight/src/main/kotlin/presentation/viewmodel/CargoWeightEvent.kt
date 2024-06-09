package presentation.viewmodel

import data.model.CalcSave


interface CargoWeightEvent

class SaveCalcEvent(val name: String, val description: String) : CargoWeightEvent
class CalcWeightWithoutRods() : CargoWeightEvent
class CalcWeightWithRods() : CargoWeightEvent
class ChangeUnitOfMeasureWithoutRods() : CargoWeightEvent
class ChangeUnitOfMeasureWithRods() : CargoWeightEvent
class SetSavedCalc(val param: CalcSave) : CargoWeightEvent
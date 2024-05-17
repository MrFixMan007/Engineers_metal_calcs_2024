package presentation.viewmodel


interface CargoWeightEvent

class SaveCalcEvent(val name: String, val description: String) : CargoWeightEvent
class CalcWeightWithoutRods() : CargoWeightEvent
class CalcWeightWithRods() : CargoWeightEvent
class ChangeUnitOfMeasureWithoutRods() : CargoWeightEvent
class ChangeUnitOfMeasureWithRods() : CargoWeightEvent
package presentation.viewmodel

interface CargoWeightEvent

class SaveCalcEvent() : CargoWeightEvent
class CalcWeightWithoutRods() : CargoWeightEvent
class CalcWeightWithRods() : CargoWeightEvent
class ChangeUnitOfMeasureWithoutRods() : CargoWeightEvent
class ChangeUnitOfMeasureWithRods() : CargoWeightEvent
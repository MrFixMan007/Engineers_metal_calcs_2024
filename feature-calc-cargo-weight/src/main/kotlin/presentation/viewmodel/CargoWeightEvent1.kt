package presentation.viewmodel

interface CargoWeightEvent1

class SaveCalcEvent() : CargoWeightEvent1
class CalcWeightWithoutRods() : CargoWeightEvent1
class CalcWeightWithRods() : CargoWeightEvent1
class ChangeUnitOfMeasureWithoutRods() : CargoWeightEvent1
class ChangeUnitOfMeasureWithRods() : CargoWeightEvent1
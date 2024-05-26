package presentation.viewmodel

import androidx.lifecycle.ViewModel
import calculation.CalcTemperatureFasonUseCase
import data.model.CalcSave
import data.model.CalcType
import data.model.TypeEnum
import data.model.calcNamesEnum.CargoWeightNameEnum
import data.model.calcNamesEnum.TempLikvidusNameEnum
import data.model.container.CargoWeightContainer
import domain.model.volume.VolumeUnit
import domain.model.weight.WeightUnit
import domain.usecase.saveCalc.SaveCalcUseCase
import domain.usecase.temperatureFason.TemperatureFasonUseCase
import presentation.model.PercentMetalModel

class TempLikvidusFasonViewModel(
    private val calcUseCase: TemperatureFasonUseCase,
    private val saveCalcUseCase: SaveCalcUseCase,
    private val countRound: Int,
) : ViewModel() {

    private suspend fun save(name: String, description: String) : Boolean{
//        return saveCalcUseCase.invoke(
//            CalcSave(
//            type = CalcType(TypeEnum.WeightOfCargo),
//            name = name,
//            description = description,
//            container = CargoWeightContainer(
//                vB = VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.Vb]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.Vb]!!.value),
//                v1C = VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.V1c]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.V1c]!!.value),
//                v2C = VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.V2c]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.V2c]!!.value),
//                mB = WeightUnit(mapper.getWeight(calcUnitMap[CargoWeightNameEnum.Mb]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.Mb]!!.value),
//                mC = WeightUnit(mapper.getWeight(calcUnitMap[CargoWeightNameEnum.Mc]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.Mc]!!.value),
//            )
//        )
//        )
        return true
    }
    suspend fun send(event: TempLikvidusEvent) : Boolean{
        when (event){
            is SaveCalcEvent -> {
                return save(event.name, event.description)
            }
            is CalcFasonEvent -> {

            }
        }
        return false
    }
}
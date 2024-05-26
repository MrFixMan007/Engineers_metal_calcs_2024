package presentation.viewmodel

import androidx.lifecycle.ViewModel
import calculation.Round
import data.model.CalcSave
import data.model.CalcType
import data.model.TypeEnum
import data.model.calcNamesEnum.CargoWeightNameEnum
import data.model.container.CargoWeightContainer
import domain.model.volume.VolumeUnit
import domain.model.weight.WeightUnit
import domain.usecase.saveCalc.SaveCalcUseCase
import domain.usecase.weightCargoWithRods.WeightOfCargoWithRods
import domain.usecase.weightCargoWithRods.inputParam.WeightOfCargoWithRodsParam
import domain.usecase.weightCargoWithoutRods.WeightOfCargoWithoutRods
import domain.usecase.weightCargoWithoutRods.inputParam.WeightOfCargoWithoutRodsParam
import presentation.mapper.StringMeasureToUnitMapper
import presentation.mapper.StringResourceMapper
import presentation.model.CalcUnit

class CargoWeightSavedViewModel(
    private val calcWeightWithRods: WeightOfCargoWithRods,
    private val calcWeightWithoutRods: WeightOfCargoWithoutRods,
    private val saveCalcUseCase: SaveCalcUseCase,
    private val calcUnitMap: MutableMap<CargoWeightNameEnum, CalcUnit>,
    private val mapper : StringMeasureToUnitMapper,
    private val stringResourceMapper: StringResourceMapper,
    private val countRoundWith: Int,
    private val countRoundWithout: Int,
    var name: String? = null
) : ViewModel() {

    private fun calcWeightWithout(){
        val param = WeightOfCargoWithoutRodsParam(
            VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.Vb]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.Vb]!!.value))
        val result = calcWeightWithoutRods.invoke(param)
        calcUnitMap[CargoWeightNameEnum.Mb]!!.value = Round.invoke(result.getUnitsOfMeasure(mapper.getWeight(calcUnitMap[CargoWeightNameEnum.Mb]!!.measuredIn)), countRoundWithout)
    }

    private fun calcWeightWith(){
        val param = WeightOfCargoWithRodsParam(
            VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.V1c]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.V1c]!!.value),
            VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.V2c]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.V2c]!!.value))
        val result = calcWeightWithRods.invoke(param)
        calcUnitMap[CargoWeightNameEnum.Mc]!!.value = Round.invoke(result.getUnitsOfMeasure(mapper.getWeight(calcUnitMap[CargoWeightNameEnum.Mc]!!.measuredIn)), countRoundWith)
    }

    private fun changeMeasureWithout(){
        calcWeightWithout()
    }

    private fun changeMeasureWith(){
        calcWeightWith()
    }

    private fun setSavedValues(param: CalcSave){
        name = param.name
        val container = (param.container as CargoWeightContainer)
        calcUnitMap[CargoWeightNameEnum.Vb]!!.value = container.vB.value
        calcUnitMap[CargoWeightNameEnum.Vb]!!.measuredIn = stringResourceMapper.getString(container.vB.measuredIn)
        calcUnitMap[CargoWeightNameEnum.Mb]!!.value = container.mB.value
        calcUnitMap[CargoWeightNameEnum.Mb]!!.measuredIn = stringResourceMapper.getString(container.mB.measuredIn)

        calcUnitMap[CargoWeightNameEnum.V1c]!!.value = container.v1C.value
        calcUnitMap[CargoWeightNameEnum.V1c]!!.measuredIn = stringResourceMapper.getString(container.v1C.measuredIn)
        calcUnitMap[CargoWeightNameEnum.V2c]!!.value = container.v2C.value
        calcUnitMap[CargoWeightNameEnum.V2c]!!.measuredIn = stringResourceMapper.getString(container.v2C.measuredIn)
        calcUnitMap[CargoWeightNameEnum.Mc]!!.value = container.mC.value
        calcUnitMap[CargoWeightNameEnum.Mc]!!.measuredIn = stringResourceMapper.getString(container.mC.measuredIn)
    }

    private suspend fun save(name: String, description: String) : Boolean{
        return saveCalcUseCase.invoke(CalcSave(
            type = CalcType(TypeEnum.WeightOfCargo),
            name = name,
            description = description,
            container = CargoWeightContainer(
                vB = VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.Vb]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.Vb]!!.value),
                v1C = VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.V1c]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.V1c]!!.value),
                v2C = VolumeUnit(mapper.getVolume(calcUnitMap[CargoWeightNameEnum.V2c]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.V2c]!!.value),
                mB = WeightUnit(mapper.getWeight(calcUnitMap[CargoWeightNameEnum.Mb]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.Mb]!!.value),
                mC = WeightUnit(mapper.getWeight(calcUnitMap[CargoWeightNameEnum.Mc]!!.measuredIn), calcUnitMap[CargoWeightNameEnum.Mc]!!.value),
            )
        ))
    }

    suspend fun send(event: CargoWeightEvent) : Boolean{
        when (event){
            is SaveCalcEvent -> {
                return save(event.name, event.description)
            }
            is CalcWeightWithoutRods -> {
                calcWeightWithout()
            }
            is CalcWeightWithRods -> {
                calcWeightWith()
            }
            is ChangeUnitOfMeasureWithoutRods -> {
                changeMeasureWithout()
            }
            is ChangeUnitOfMeasureWithRods -> {
                changeMeasureWith()
            }
            is SetSavedCalc -> {
                setSavedValues(event.param)
            }
        }
        return false
    }
}
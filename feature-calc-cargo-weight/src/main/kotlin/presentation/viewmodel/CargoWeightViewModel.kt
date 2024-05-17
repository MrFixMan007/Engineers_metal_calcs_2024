package presentation.viewmodel

import androidx.lifecycle.ViewModel
import calculation.Round
import domain.model.volume.VolumeUnit
import domain.usecase.weightCargoWithRods.WeightOfCargoWithRods
import domain.usecase.weightCargoWithRods.param.WeightOfCargoWithRodsParam
import domain.usecase.weightCargoWithoutRods.WeightOfCargoWithoutRods
import domain.usecase.weightCargoWithoutRods.param.WeightOfCargoWithoutRodsParam
import presentation.fragments.Calc
import presentation.mapper.StringMeasureToUnitMapper
import presentation.model.CalcUnit

class CargoWeightViewModel(
    private val calcWeightWithRods: WeightOfCargoWithRods,
    private val calcWeightWithoutRods: WeightOfCargoWithoutRods,
//    private val saveCalc: SaveCalc TODO: будет возможность сейва расчета
    private val calcUnitMap: MutableMap<Calc, CalcUnit>,
    private val mapper : StringMeasureToUnitMapper,
    private val countRoundWith: Int,
    private val countRoundWithout: Int,
) : ViewModel() {

    private fun calcWeightWithout(){
        val param = WeightOfCargoWithoutRodsParam(
            VolumeUnit(mapper.getVolume(calcUnitMap[Calc.Vb]!!.measuredIn), calcUnitMap[Calc.Vb]!!.value))
        val result = calcWeightWithoutRods.invoke(param)
        calcUnitMap[Calc.Mb]!!.value = Round.invoke(result.getUnitsOfMeasure(mapper.getWeight(calcUnitMap[Calc.Mb]!!.measuredIn)), countRoundWithout)
    }

    private fun calcWeightWith(){
        val param = WeightOfCargoWithRodsParam(
            VolumeUnit(mapper.getVolume(calcUnitMap[Calc.V1c]!!.measuredIn), calcUnitMap[Calc.V1c]!!.value),
            VolumeUnit(mapper.getVolume(calcUnitMap[Calc.V2c]!!.measuredIn), calcUnitMap[Calc.V2c]!!.value))
        val result = calcWeightWithRods.invoke(param)
        calcUnitMap[Calc.Mc]!!.value = Round.invoke(result.getUnitsOfMeasure(mapper.getWeight(calcUnitMap[Calc.Mc]!!.measuredIn)), countRoundWith)
    }

    private fun changeMeasureWithout(){
        calcWeightWithout()
    }

    private fun changeMeasureWith(){
        calcWeightWith()
    }

    private fun save(){
        // TODO: вызываем диалоги сохранения
    }

    fun send(event: CargoWeightEvent){
        when (event){
            is SaveCalcEvent -> {
                save()
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
        }
    }
}
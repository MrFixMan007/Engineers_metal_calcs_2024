package calculation

import domain.model.weight.Weight
import domain.model.weight.WeightUnit
import domain.usecase.weightCargoWithoutRods.WeightOfCargoWithoutRods
import domain.usecase.weightCargoWithoutRods.inputParam.WeightOfCargoWithoutRodsParam

class CalcWeightOfCargoWithoutRods : WeightOfCargoWithoutRods {
    override fun invoke(param: WeightOfCargoWithoutRodsParam): Weight {
        val answer = param.v.m3() * coef
        return WeightUnit.t(answer)
    }
    companion object{
        private const val coef = 9.1f
    }
}
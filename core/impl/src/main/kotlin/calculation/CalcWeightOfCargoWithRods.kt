package calculation

import domain.model.weight.Weight
import domain.model.weight.WeightUnit
import domain.usecase.weightCargoWithRods.WeightOfCargoWithRods
import domain.usecase.weightCargoWithRods.inputParam.WeightOfCargoWithRodsParam

class CalcWeightOfCargoWithRods : WeightOfCargoWithRods {
    override fun invoke(param: WeightOfCargoWithRodsParam): Weight {
        return WeightUnit.t((k1 * param.vDo.m3() + k2 * param.vBez.m3()) * coef)
    }
    companion object{
        // Производственный коэфициент запаса на удельный вес жидкого металла
        private const val coef = 1.3f

        // Производственный коэфициент
        private const val k1 = 7
        // Производственный коэфициент
        private const val k2 = 5
    }
}
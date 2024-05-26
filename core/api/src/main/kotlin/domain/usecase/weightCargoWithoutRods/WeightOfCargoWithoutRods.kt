package domain.usecase.weightCargoWithoutRods

import domain.model.weight.Weight
import domain.usecase.weightCargoWithoutRods.inputParam.WeightOfCargoWithoutRodsParam

interface WeightOfCargoWithoutRods : (WeightOfCargoWithoutRodsParam) -> Weight
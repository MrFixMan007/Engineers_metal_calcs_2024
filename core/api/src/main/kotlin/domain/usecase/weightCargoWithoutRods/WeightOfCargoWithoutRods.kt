package domain.usecase.weightCargoWithoutRods

import domain.model.weight.Weight
import domain.usecase.weightCargoWithoutRods.param.WeightOfCargoWithoutRodsParam

interface WeightOfCargoWithoutRods : (WeightOfCargoWithoutRodsParam) -> Weight
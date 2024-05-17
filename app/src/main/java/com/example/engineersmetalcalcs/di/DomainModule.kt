package com.example.engineersmetalcalcs.di

import calculation.CalcWeightOfCargoWithRods
import calculation.CalcWeightOfCargoWithoutRods
import domain.usecase.weightCargoWithRods.WeightOfCargoWithRods
import domain.usecase.weightCargoWithoutRods.WeightOfCargoWithoutRods
import org.koin.dsl.module

val domainModule = module {

    single<WeightOfCargoWithRods> {
        CalcWeightOfCargoWithRods()
    }

    single<WeightOfCargoWithoutRods> {
        CalcWeightOfCargoWithoutRods()
    }

}
package com.example.engineersmetalcalcs.di

import calculation.CalcTemperatureFasonUseCase
import calculation.CalcTemperatureIngotUseCase
import calculation.CalcWeightOfCargoWithRodsUseCase
import calculation.CalcWeightOfCargoWithoutRodsUseCase
import domain.usecase.saveCalc.GetAllCalcInfoUseCase
import domain.usecase.saveCalc.GetAllCalcSavesUseCase
import domain.usecase.saveCalc.SaveCalcUseCase
import domain.usecase.temperatureFason.TemperatureFasonUseCase
import domain.usecase.temperatureIngot.TemperatureIngotUseCase
import domain.usecase.weightCargoWithRods.WeightOfCargoWithRodsUseCase
import domain.usecase.weightCargoWithoutRods.WeightOfCargoWithoutRodsUseCase
import org.koin.dsl.module
import save.GetAllCalcInfo
import save.GetAllCalcSaves
import save.SaveCalc

val domainModule = module {

    single<WeightOfCargoWithRodsUseCase> {
        CalcWeightOfCargoWithRodsUseCase()
    }

    single<WeightOfCargoWithoutRodsUseCase> {
        CalcWeightOfCargoWithoutRodsUseCase()
    }

    single<TemperatureFasonUseCase> {
        CalcTemperatureFasonUseCase()
    }

    single<TemperatureIngotUseCase> {
        CalcTemperatureIngotUseCase()
    }

    single<SaveCalcUseCase> { SaveCalc(get()) }
    single<GetAllCalcInfoUseCase> { GetAllCalcInfo(get()) }
    single<GetAllCalcSavesUseCase> { GetAllCalcSaves(get()) }
}
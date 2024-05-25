package di

import calculation.CalcWeightOfCargoWithRods
import calculation.CalcWeightOfCargoWithoutRods
import domain.usecase.saveCalc.GetAllCalcInfoUseCase
import domain.usecase.saveCalc.GetAllCalcSavesUseCase
import domain.usecase.saveCalc.SaveCalcUseCase
import domain.usecase.weightCargoWithRods.WeightOfCargoWithRods
import domain.usecase.weightCargoWithoutRods.WeightOfCargoWithoutRods
import org.koin.dsl.module
import save.GetAllCalcInfo
import save.GetAllCalcSaves
import save.SaveCalc

val domainModule = module {

    single<WeightOfCargoWithRods> {
        CalcWeightOfCargoWithRods()
    }

    single<WeightOfCargoWithoutRods> {
        CalcWeightOfCargoWithoutRods()
    }

    single<SaveCalcUseCase> { SaveCalc(get()) }
    single<GetAllCalcInfoUseCase> { GetAllCalcInfo(get()) }
    single<GetAllCalcSavesUseCase> { GetAllCalcSaves(get()) }
}
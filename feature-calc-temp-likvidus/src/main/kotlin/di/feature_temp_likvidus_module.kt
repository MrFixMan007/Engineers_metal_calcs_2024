package di

import data.model.calcNamesEnum.TempLikvidusNameEnum
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.model.PercentMetalModel
import presentation.viewmodel.TempLikvidusFasonSavedViewModel
import presentation.viewmodel.TempLikvidusFasonViewModel
import presentation.viewmodel.TempLikvidusIngotSavedViewModel
import presentation.viewmodel.TempLikvidusIngotViewModel

val feature_temp_likvidus_module = module {

    viewModel<TempLikvidusFasonViewModel> {
        TempLikvidusFasonViewModel(
            calcUseCase = get(),
            saveCalcUseCase = get(),
            countRound = 3
        )
    }

    viewModel<TempLikvidusIngotViewModel> {
        TempLikvidusIngotViewModel(
            calcUseCase = get(),
            saveCalcUseCase = get(),
            countRound = 3
        )
    }

    viewModel<TempLikvidusFasonSavedViewModel> {
        TempLikvidusFasonSavedViewModel(
            calcUseCase = get(),
            saveCalcUseCase = get(),
            countRound = 3
        )
    }

    viewModel<TempLikvidusIngotSavedViewModel> {
        TempLikvidusIngotSavedViewModel(
            calcUseCase = get(),
            saveCalcUseCase = get(),
            countRound = 3
        )
    }

    single <Map<TempLikvidusNameEnum, PercentMetalModel>>(named("percentMetalsFason")){
        mapOf(
            TempLikvidusNameEnum.C to PercentMetalModel(name = TempLikvidusNameEnum.C.name),
            TempLikvidusNameEnum.Si to PercentMetalModel(name = TempLikvidusNameEnum.Si.name),
            TempLikvidusNameEnum.Mn to PercentMetalModel(name = TempLikvidusNameEnum.Mn.name),
            TempLikvidusNameEnum.P to PercentMetalModel(name = TempLikvidusNameEnum.P.name),
            TempLikvidusNameEnum.S to PercentMetalModel(name = TempLikvidusNameEnum.S.name),
            TempLikvidusNameEnum.Cr to PercentMetalModel(name = TempLikvidusNameEnum.Cr.name),
            TempLikvidusNameEnum.Ni to PercentMetalModel(name = TempLikvidusNameEnum.Ni.name),
            TempLikvidusNameEnum.Cu to PercentMetalModel(name = TempLikvidusNameEnum.Cu.name),
            TempLikvidusNameEnum.Al to PercentMetalModel(name = TempLikvidusNameEnum.Al.name),
            TempLikvidusNameEnum.Ti to PercentMetalModel(name = TempLikvidusNameEnum.Ti.name),
            TempLikvidusNameEnum.V to PercentMetalModel(name = TempLikvidusNameEnum.V.name),
            TempLikvidusNameEnum.Mo to PercentMetalModel(name = TempLikvidusNameEnum.Mo.name),
            TempLikvidusNameEnum.W to PercentMetalModel(name = TempLikvidusNameEnum.W.name),
            TempLikvidusNameEnum.Co to PercentMetalModel(name = TempLikvidusNameEnum.Co.name),
        )
    }

    single <Map<TempLikvidusNameEnum, PercentMetalModel>>(named("percentMetalsIngot")){
        mapOf(
            TempLikvidusNameEnum.C to PercentMetalModel(name = TempLikvidusNameEnum.C.name),
            TempLikvidusNameEnum.Si to PercentMetalModel(name = TempLikvidusNameEnum.Si.name),
            TempLikvidusNameEnum.Mn to PercentMetalModel(name = TempLikvidusNameEnum.Mn.name),
            TempLikvidusNameEnum.P to PercentMetalModel(name = TempLikvidusNameEnum.P.name),
            TempLikvidusNameEnum.S to PercentMetalModel(name = TempLikvidusNameEnum.S.name),
            TempLikvidusNameEnum.Cr to PercentMetalModel(name = TempLikvidusNameEnum.Cr.name),
            TempLikvidusNameEnum.Ni to PercentMetalModel(name = TempLikvidusNameEnum.Ni.name),
            TempLikvidusNameEnum.Cu to PercentMetalModel(name = TempLikvidusNameEnum.Cu.name),
            TempLikvidusNameEnum.Al to PercentMetalModel(name = TempLikvidusNameEnum.Al.name),
            TempLikvidusNameEnum.Ti to PercentMetalModel(name = TempLikvidusNameEnum.Ti.name),
            TempLikvidusNameEnum.V to PercentMetalModel(name = TempLikvidusNameEnum.V.name),
            TempLikvidusNameEnum.Mo to PercentMetalModel(name = TempLikvidusNameEnum.Mo.name),
            TempLikvidusNameEnum.W to PercentMetalModel(name = TempLikvidusNameEnum.W.name),
            TempLikvidusNameEnum.Co to PercentMetalModel(name = TempLikvidusNameEnum.Co.name),
        )
    }

//    single <Map<TempLikvidusNameEnum, PercentMetalModel>>(named("percentMetalsFasonForSave")){
//        mapOf(
//            TempLikvidusNameEnum.W to PercentMetalModel(name = TempLikvidusNameEnum.W.name),
//            TempLikvidusNameEnum.Cr to PercentMetalModel(name = TempLikvidusNameEnum.Cr.name),
//            TempLikvidusNameEnum.Co to PercentMetalModel(name = TempLikvidusNameEnum.Co.name),
//            TempLikvidusNameEnum.Mo to PercentMetalModel(name = TempLikvidusNameEnum.Mo.name),
//            TempLikvidusNameEnum.V to PercentMetalModel(name = TempLikvidusNameEnum.V.name),
//            TempLikvidusNameEnum.Al to PercentMetalModel(name = TempLikvidusNameEnum.Al.name),
//            TempLikvidusNameEnum.Ni to PercentMetalModel(name = TempLikvidusNameEnum.Ni.name),
//            TempLikvidusNameEnum.Mn to PercentMetalModel(name = TempLikvidusNameEnum.Mn.name),
//            TempLikvidusNameEnum.Cu to PercentMetalModel(name = TempLikvidusNameEnum.Cu.name),
//            TempLikvidusNameEnum.Si to PercentMetalModel(name = TempLikvidusNameEnum.Si.name),
//            TempLikvidusNameEnum.Ti to PercentMetalModel(name = TempLikvidusNameEnum.Ti.name),
//            TempLikvidusNameEnum.S to PercentMetalModel(name = TempLikvidusNameEnum.S.name),
//            TempLikvidusNameEnum.P to PercentMetalModel(name = TempLikvidusNameEnum.P.name),
//            TempLikvidusNameEnum.C to PercentMetalModel(name = TempLikvidusNameEnum.C.name)
//        )
//    }
//
//    single <Map<TempLikvidusNameEnum, PercentMetalModel>>(named("percentMetalsIngotForSave")){
//        mapOf(
//            TempLikvidusNameEnum.W to PercentMetalModel(name = TempLikvidusNameEnum.W.name),
//            TempLikvidusNameEnum.Cr to PercentMetalModel(name = TempLikvidusNameEnum.Cr.name),
//            TempLikvidusNameEnum.Co to PercentMetalModel(name = TempLikvidusNameEnum.Co.name),
//            TempLikvidusNameEnum.Mo to PercentMetalModel(name = TempLikvidusNameEnum.Mo.name),
//            TempLikvidusNameEnum.V to PercentMetalModel(name = TempLikvidusNameEnum.V.name),
//            TempLikvidusNameEnum.Al to PercentMetalModel(name = TempLikvidusNameEnum.Al.name),
//            TempLikvidusNameEnum.Ni to PercentMetalModel(name = TempLikvidusNameEnum.Ni.name),
//            TempLikvidusNameEnum.Mn to PercentMetalModel(name = TempLikvidusNameEnum.Mn.name),
//            TempLikvidusNameEnum.Cu to PercentMetalModel(name = TempLikvidusNameEnum.Cu.name),
//            TempLikvidusNameEnum.Si to PercentMetalModel(name = TempLikvidusNameEnum.Si.name),
//            TempLikvidusNameEnum.Ti to PercentMetalModel(name = TempLikvidusNameEnum.Ti.name),
//            TempLikvidusNameEnum.S to PercentMetalModel(name = TempLikvidusNameEnum.S.name),
//            TempLikvidusNameEnum.P to PercentMetalModel(name = TempLikvidusNameEnum.P.name),
//            TempLikvidusNameEnum.C to PercentMetalModel(name = TempLikvidusNameEnum.C.name)
//        )
//    }
}
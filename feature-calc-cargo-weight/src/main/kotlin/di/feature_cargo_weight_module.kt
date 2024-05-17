package di

import android.content.Context
import metalcalcs.feature_calc_cargo_weight.R
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.fragments.Calc
import presentation.fragments.CargoWeightFragment
import presentation.mapper.StringResourcesParam
import presentation.model.CalcUnit
import presentation.viewmodel.CargoWeightViewModel

val feature_cargo_weight_module = module {

    viewModel<CargoWeightViewModel>{
        CargoWeightViewModel(
            calcWeightWithoutRods = get(),
            calcWeightWithRods = get(),
            calcUnitMap = get(),
            mapper = get(),
            countRoundWith = 2,
            countRoundWithout = 2
        )
    }

    factory<List<CalcUnit>>(named("calcUnits1")) {
        val map = get<Map<Calc, CalcUnit>>()
        listOf(map[Calc.Vb]!!, map[Calc.Mb]!!)
    }
    factory<List<CalcUnit>>(named("calcUnits2")) {
        val map = get<Map<Calc, CalcUnit>>()
        listOf(map[Calc.V1c]!!, map[Calc.V2c]!!, map[Calc.Mc]!!)
    }

    factory { CargoWeightFragment() }

    single<StringResourcesParam>(named("cargoWeight")) {
        StringResourcesParam(
        resIdForArrayWeight = R.array.unitsOfWeight,
        resIdForArrayVolume = R.array.unitsOfVolume) }

    single<Map<Calc, CalcUnit>> {
        mapOf(
            Calc.Vb to CalcUnit(
                description = get<Context>().resources.getString(R.string.vb),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.m3),
                type = CalcUnit.INPUT_STRONG_MEASURE),
            Calc.Mb to CalcUnit(
                description = get<Context>().resources.getString(R.string.mb),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.t),
                type = CalcUnit.OUTPUT),
            Calc.V1c to CalcUnit(
                description = get<Context>().resources.getString(R.string.v1c),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.m3),
                type = CalcUnit.INPUT_STRONG_MEASURE),
            Calc.V2c to CalcUnit(
                description = get<Context>().resources.getString(R.string.v2c),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.m3),
                type = CalcUnit.INPUT_STRONG_MEASURE),
            Calc.Mc to CalcUnit(
                description = get<Context>().resources.getString(R.string.mc),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.t),
                type = CalcUnit.OUTPUT),
            )
    }
}
package di

import android.content.Context
import data.model.calcNamesEnum.CargoWeightNameEnum
import metalcalcs.feature_calc_cargo_weight.R
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.fragments.CargoWeightFragment
import presentation.mapper.StringResourceMapper
import presentation.mapper.StringResourcesParam
import presentation.model.CalcUnit
import presentation.viewmodel.CargoWeightViewModel

val feature_cargo_weight_module = module {

    viewModel<CargoWeightViewModel>{
        val map : StringResourceMapper = get()
        map.setValues(get(named("cargoWeight")))
        CargoWeightViewModel(
            calcWeightWithoutRods = get(),
            calcWeightWithRods = get(),
            calcUnitMap = get(),
            mapper = get(),
            countRoundWith = 2,
            countRoundWithout = 2,
            saveCalcUseCase = get(),
            stringResourceMapper = map
        )
    }

    factory<List<CalcUnit>>(named("calcUnits1")) {
        val map = get<Map<CargoWeightNameEnum, CalcUnit>>()
        listOf(map[CargoWeightNameEnum.Vb]!!, map[CargoWeightNameEnum.Mb]!!)
    }
    factory<List<CalcUnit>>(named("calcUnits2")) {
        val map = get<Map<CargoWeightNameEnum, CalcUnit>>()
        listOf(map[CargoWeightNameEnum.V1c]!!, map[CargoWeightNameEnum.V2c]!!, map[CargoWeightNameEnum.Mc]!!)
    }

    factory { CargoWeightFragment() }

    single<StringResourcesParam>(named("cargoWeight")) {
        StringResourcesParam(
        resIdForArrayWeight = R.array.unitsOfWeight,
        resIdForArrayVolume = R.array.unitsOfVolume) }

    single<Map<CargoWeightNameEnum, CalcUnit>> {
        mapOf(
            CargoWeightNameEnum.Vb to CalcUnit(
                description = get<Context>().resources.getString(R.string.vb),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.m3),
                type = CalcUnit.INPUT_STRONG_MEASURE),
            CargoWeightNameEnum.Mb to CalcUnit(
                description = get<Context>().resources.getString(R.string.mb),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.t),
                type = CalcUnit.OUTPUT),
            CargoWeightNameEnum.V1c to CalcUnit(
                description = get<Context>().resources.getString(R.string.v1c),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.m3),
                type = CalcUnit.INPUT_STRONG_MEASURE),
            CargoWeightNameEnum.V2c to CalcUnit(
                description = get<Context>().resources.getString(R.string.v2c),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.m3),
                type = CalcUnit.INPUT_STRONG_MEASURE),
            CargoWeightNameEnum.Mc to CalcUnit(
                description = get<Context>().resources.getString(R.string.mc),
                measuredIn = get<Context>().resources.getString(metalcalcs.core_ui.R.string.t),
                type = CalcUnit.OUTPUT),
            )
    }
}
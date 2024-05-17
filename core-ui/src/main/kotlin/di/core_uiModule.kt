package di

import org.koin.dsl.module
import presentation.mapper.StringMeasureToUnitMapper
import presentation.mapper.StringResourceMapper

val core_uiModule = module {
    factory <StringResourceMapper>{ StringResourceMapper(get()) }
    factory <StringMeasureToUnitMapper>{ StringMeasureToUnitMapper(get()) }
}
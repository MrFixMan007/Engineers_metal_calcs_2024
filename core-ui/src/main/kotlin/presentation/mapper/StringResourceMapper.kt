package presentation.mapper

import android.content.Context
import domain.model.length.LengthMeasure
import domain.model.volume.VolumeMeasure
import domain.model.weight.WeightMeasure
import metalcalcs.core_ui.R

class StringResourceMapper(val context: Context,
                           resIdForArrayLength: Int = R.array.unitsOfLengthDefault,
                           resIdForArrayWeight: Int = R.array.unitsOfWeightDefault,
                           resIdForArrayVolume: Int = R.array.unitsOfVolumeDefault) {

    private val stringArrayMap = mutableMapOf(
        context.getString(R.string.mm) to resIdForArrayLength,
        context.getString(R.string.cm) to resIdForArrayLength,
        context.getString(R.string.dm) to resIdForArrayLength,
        context.getString(R.string.m) to resIdForArrayLength,
        context.getString(R.string.km) to resIdForArrayLength,

        context.getString(R.string.g) to resIdForArrayWeight,
        context.getString(R.string.kg) to resIdForArrayWeight,
        context.getString(R.string.c) to resIdForArrayWeight,
        context.getString(R.string.t) to resIdForArrayWeight,

        context.getString(R.string.mm3) to resIdForArrayVolume,
        context.getString(R.string.cm3) to resIdForArrayVolume,
        context.getString(R.string.dm3) to resIdForArrayVolume,
        context.getString(R.string.m3) to resIdForArrayVolume
    )
    private val stringMeasureArrayMap = mutableMapOf(
        LengthMeasure.Mm to context.getString(R.string.mm),
        LengthMeasure.Cm to context.getString(R.string.cm),
        LengthMeasure.Dm to context.getString(R.string.dm),
        LengthMeasure.M to context.getString(R.string.m),
        LengthMeasure.Km to context.getString(R.string.km),

        WeightMeasure.G to context.getString(R.string.g),
        WeightMeasure.Kg to context.getString(R.string.kg),
        WeightMeasure.C to context.getString(R.string.c),
        WeightMeasure.T to context.getString(R.string.t),

        VolumeMeasure.Mm3 to context.getString(R.string.mm3),
        VolumeMeasure.Cm3 to context.getString(R.string.cm3),
        VolumeMeasure.Dm3 to context.getString(R.string.dm3),
        VolumeMeasure.M3 to context.getString(R.string.m3)
    )

    fun setValues(param: StringResourcesParam){
        if (param.resIdForArrayLength != null){
            stringArrayMap[context.getString(R.string.mm)] = param.resIdForArrayLength
            stringArrayMap[context.getString(R.string.cm)] = param.resIdForArrayLength
            stringArrayMap[context.getString(R.string.dm)] = param.resIdForArrayLength
            stringArrayMap[context.getString(R.string.m)] = param.resIdForArrayLength
            stringArrayMap[context.getString(R.string.km)] = param.resIdForArrayLength
        }
        if (param.resIdForArrayWeight != null){
            stringArrayMap[context.getString(R.string.g)] = param.resIdForArrayWeight
            stringArrayMap[context.getString(R.string.kg)] = param.resIdForArrayWeight
            stringArrayMap[context.getString(R.string.c)] = param.resIdForArrayWeight
            stringArrayMap[context.getString(R.string.t)] = param.resIdForArrayWeight
        }
        if (param.resIdForArrayVolume != null){
            stringArrayMap[context.getString(R.string.mm3)] = param.resIdForArrayVolume
            stringArrayMap[context.getString(R.string.cm3)] = param.resIdForArrayVolume
            stringArrayMap[context.getString(R.string.dm3)] = param.resIdForArrayVolume
            stringArrayMap[context.getString(R.string.m3)] = param.resIdForArrayVolume
        }
    }

    fun getStringArrayResourceId(input: String): Int {
        return stringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun getString(input: LengthMeasure): String {
        return stringMeasureArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun getString(input: WeightMeasure): String {
        return stringMeasureArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun getString(input: VolumeMeasure): String {
        return stringMeasureArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
}
package presentation.mapper

import android.content.Context
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
}
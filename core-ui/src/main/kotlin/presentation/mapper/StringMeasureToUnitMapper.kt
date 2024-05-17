package presentation.mapper

import android.content.Context
import domain.model.length.LengthMeasure
import domain.model.volume.VolumeMeasure
import domain.model.weight.WeightMeasure
import metalcalcs.core_ui.R

class StringMeasureToUnitMapper(val context: Context) {
    private val lengthStringArrayMap = mapOf(
        context.getString(R.string.mm) to LengthMeasure.Mm,
        context.getString(R.string.cm) to LengthMeasure.Cm,
        context.getString(R.string.dm) to LengthMeasure.Dm,
        context.getString(R.string.m) to LengthMeasure.M,
        context.getString(R.string.km) to LengthMeasure.Km,
    )
    private val weightStringArrayMap = mapOf(
        context.getString(R.string.g) to WeightMeasure.G,
        context.getString(R.string.kg) to WeightMeasure.Kg,
        context.getString(R.string.c) to WeightMeasure.C,
        context.getString(R.string.t) to WeightMeasure.T,
    )
    private val volumeStringArrayMap = mapOf(
        context.getString(R.string.mm3) to VolumeMeasure.Mm3,
        context.getString(R.string.cm3) to VolumeMeasure.Cm3,
        context.getString(R.string.dm3) to VolumeMeasure.Dm3,
        context.getString(R.string.m3) to VolumeMeasure.M3
    )

    fun getLength(input: String): LengthMeasure{
        return lengthStringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun getWeight(input: String): WeightMeasure{
        return weightStringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun getVolume(input: String): VolumeMeasure{
        return volumeStringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
}
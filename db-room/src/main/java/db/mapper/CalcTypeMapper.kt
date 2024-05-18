package db.mapper

import data.model.CalcType
import data.model.TypeEnum
import domain.model.length.LengthMeasure
import domain.model.volume.VolumeMeasure
import domain.model.weight.WeightMeasure

object CalcTypeMapper {

    private val lengthStringArrayMap = mapOf(
        LengthMeasure.Mm.name to LengthMeasure.Mm,
        LengthMeasure.Cm.name to LengthMeasure.Cm,
        LengthMeasure.Dm.name to LengthMeasure.Dm,
        LengthMeasure.M.name to LengthMeasure.M,
        LengthMeasure.Km.name to LengthMeasure.Km,
    )
    private val weightStringArrayMap = mapOf(
        WeightMeasure.G.name to WeightMeasure.G,
        WeightMeasure.Kg.name to WeightMeasure.Kg,
        WeightMeasure.C.name to WeightMeasure.C,
        WeightMeasure.T.name to WeightMeasure.T,
    )
    private val volumeStringArrayMap = mapOf(
        VolumeMeasure.Mm3.name to VolumeMeasure.Mm3,
        VolumeMeasure.Cm3.name to VolumeMeasure.Cm3,
        VolumeMeasure.Dm3.name to VolumeMeasure.Dm3,
        VolumeMeasure.M3.name to VolumeMeasure.M3
    )

    fun getLength(input: String): LengthMeasure {
        return lengthStringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun getWeight(input: String): WeightMeasure {
        return weightStringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun getVolume(input: String): VolumeMeasure {
        return volumeStringArrayMap[input]
            ?: throw IllegalArgumentException("Нет соответствия для строки: $input")
    }
    fun toCalcType(nameType: String): CalcType?{
        when (nameType){
            TypeEnum.WeightOfCargo.name -> return CalcType(TypeEnum.WeightOfCargo)
        }
        return null
    }
}
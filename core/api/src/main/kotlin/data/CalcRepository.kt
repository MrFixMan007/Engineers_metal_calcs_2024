package data

interface CalcRepository {
    fun getAllCalcs() : List<CalcInfo>
    fun getAllTypes() : List<CalcType>
}
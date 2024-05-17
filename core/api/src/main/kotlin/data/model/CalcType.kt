package data.model

data class CalcType(val typeEnum: TypeEnum, val superType: CalcType? = null)
import data.model.CalcSave

object GlobalParameter {
    private var globalSave: CalcSave? = null

    fun getCalcSave() : CalcSave?{
        if (globalSave == null) return null
        val returnSave = globalSave!!.copy()
        globalSave = null
        return returnSave
    }
    fun setCalcSave(calcSave: CalcSave){
        globalSave = calcSave.copy()
    }
    fun saveIsEmpty():Boolean{
        return globalSave == null
    }
    fun getSaveName():String?{
        if (globalSave != null)
            return globalSave!!.name
        return null
    }
}
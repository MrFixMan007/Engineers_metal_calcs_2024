import data.model.CalcSave

object GlobalParameter {
    private var globalSave: CalcSave? = null
    private var savesChanged: Boolean = false

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
    fun getSavesChanged(): Boolean{
        if(savesChanged){
            savesChanged = false
            return true
        }
        return false
    }
    fun setSavesChanged(){
        savesChanged = true
    }
}
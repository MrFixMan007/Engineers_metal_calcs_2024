package com.example.engineersmetalcalcs

import com.example.engineersmetalcalcs.db.entities.Save

object MyGlobalParameters{
    private var globalSave: Save? = null

    fun getSave():Save?{
        return globalSave
    }
    fun setSave(save: Save){
        globalSave = save
    }
    fun saveIsNull(): Boolean{
        return globalSave == null
    }
    fun getSaveName(): String{
        val name = globalSave?.name
        globalSave = null
        return name!!
    }
}
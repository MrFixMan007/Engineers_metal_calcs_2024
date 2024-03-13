package com.example.engineersmetalcalcs

import com.example.engineersmetalcalcs.db.entities.Save

object MyGlobalParameters{
    private var globalSave: Save? = null

    fun getSave():Save?{
        return if (globalSave != null) {
            val save = globalSave
            globalSave = null
            save
        } else null
    }
    fun setSave(save: Save){
        globalSave = save
    }
}
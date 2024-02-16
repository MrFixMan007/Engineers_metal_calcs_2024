package com.example.engineersmetalcalcs

import com.example.engineersmetalcalcs.db.entities.Save

class MyGlobalParameters{
    companion object{
        private var instance: MyGlobalParameters? = null
        @Synchronized
        fun getInstance(): MyGlobalParameters? {
            if (instance == null){
                instance = MyGlobalParameters()
            }
            return instance
        }
    }
    var globalSave: Save? = null
    var globalListIds: ArrayList<Int> = ArrayList()
}
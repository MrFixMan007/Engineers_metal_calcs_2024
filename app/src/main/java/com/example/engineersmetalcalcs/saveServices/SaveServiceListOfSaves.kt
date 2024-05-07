package com.example.engineersmetalcalcs.saveServices

import android.content.Context
import android.util.Log
import com.example.engineersmetalcalcs.db.AppDatabase
import com.example.engineersmetalcalcs.db.entities.Save
import com.example.engineersmetalcalcs.listItem.SaveItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class SaveServiceListOfSaves(val context: Context, val listener: Listener) {
    val db = AppDatabase.getInstance(context)
    private val saveDao = db.saveDao()
    private val characterDao = db.characterDao()
    private val valueDao = db.valueDao()
    private val saves = mutableListOf<SaveItem>()
    private val result = StringBuilder("")
    suspend fun get() = withContext(Dispatchers.IO){

        val listOfSaves = saveDao?.all
        for (item in listOfSaves!!){
            result.clear()
            val characters = characterDao?.getResults(item.typeIdFk)!!
            for (i in characters.indices){
                val value = valueDao?.getByCharacterIdFkAndSaveIdFk(characters[i].id!!, item.id!!)
                if (i != characters.size-1 && value != null)
                    result.appendLine(value.value)
                else if (value != null)
                    result.append(value.value)
            }
            if(result.isNotEmpty())
                saves.add(SaveItem(Save(item.id!!, item.name, item.date, item.typeIdFk, item.description), result.toString()))
            else
                saves.add(SaveItem(Save(item.id!!, item.name, item.date, item.typeIdFk, item.description), ""))
        }
        listener.setSaves(saves)
    }

    suspend fun deleteSave(save: SaveItem) = withContext(Dispatchers.IO){
        saveDao?.deleteById(save.save.id!!)
    }

    suspend fun saveWasChanged(save: SaveItem) = withContext(Dispatchers.IO){
        saveDao?.update(save.save)
    }

    interface Listener{
        suspend fun setSaves(saves: List<SaveItem>)
    }
}
package com.example.engineersmetalcalcs

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.engineersmetalcalcs.db.AppDatabase
import com.example.engineersmetalcalcs.db.entities.Save
import com.example.engineersmetalcalcs.db.entities.Type
import com.example.engineersmetalcalcs.listItem.CalcUnit
import com.example.engineersmetalcalcs.saveServices.SaveServiceCargoWeight
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SaveDbTest {
//    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//    private val db = AppDatabase.getInstance(context = appContext)
//
//    private val saveDao = db?.saveDao()
//    private val typeDao = db?.typeDao()
//
//    private lateinit var types:List<Type>
//    private var typeId: Long? = null
//
//    private var calcUnits: ArrayList<CalcUnit> = ArrayList()
//    private var map = mutableMapOf<String, CalcUnit>()
//
//    private lateinit var saveServiceCargoWeight: SaveServiceCargoWeight
//
//    @Before
//    fun prepare(){
//        types = typeDao?.all!!
//        typeId = types.find { it.name == appContext.getString(R.string.type1) }?.id
//        print("types:\n $types")
//        print("type:\n $typeId")
//
//        saveServiceCargoWeight = SaveServiceCargoWeight(calcUnits, map, appContext, appContext.resources.getString(R.string.type1))
//        print("saveServiceCargoWeight:\n$saveServiceCargoWeight")
//    }
//
//    @Test
//    fun save(){
//        saveDao?.insert(Save(name = "save 1", typeIdFk = typeId!!))
//        assertEquals(saveDao?.countOfSaves, 1)
//    }
//
//    @Test
//    fun save2() = runBlocking{
//        saveServiceCargoWeight.get()
//        calcUnits[0].value=0f
//        calcUnits[1].value=0f
//
//        saveServiceCargoWeight.set("test", "desc")
//
//        assertEquals(saveDao?.last?.description, "desc")
//    }
//
//    @Test
//    fun save3() = runBlocking {
//        saveServiceCargoWeight.get()
//        calcUnits[0].value=0f
//        calcUnits[1].value=0f
//
//        saveServiceCargoWeight.set("test", "desc")
//        MyGlobalParameters.setSave(saveDao?.last!!)
//
//        saveServiceCargoWeight.get()
//
//        Log.i("calcUnits", "$calcUnits")
//        assertEquals(calcUnits[0].value, 0f)
//    }
//
//    @Test
//    fun checkCount() = runBlocking {
//        saveServiceCargoWeight.get()
//        val size = calcUnits.size
//        saveServiceCargoWeight.get()
//        assertEquals(calcUnits.size, size)
//    }
}
package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineersmetalcalcs.MyGlobalParameters
import com.example.engineersmetalcalcs.adapters.LongCalcAdapter
import com.example.engineersmetalcalcs.databinding.FragmentCargoWeightBinding
import com.example.engineersmetalcalcs.db.AppDatabase
import com.example.engineersmetalcalcs.listItem.CalcUnit
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.calcServices.WeightCargoService
import com.example.engineersmetalcalcs.db.dao.CharacterDao
import com.example.engineersmetalcalcs.db.dao.TypeDao
import com.example.engineersmetalcalcs.db.entities.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CargoWeightFragment : Fragment(), LongCalcAdapter.Listener {
    private lateinit var binding: FragmentCargoWeightBinding

    private val adapter = LongCalcAdapter(this)
    private val adapter1 = LongCalcAdapter(this)

    private var calcUnits: ArrayList<CalcUnit> = ArrayList()
    private var calcUnits1: ArrayList<CalcUnit> = ArrayList()

    private var myGlobalParameters = MyGlobalParameters.getInstance()
    private lateinit var db: AppDatabase
    private lateinit var characterDao:CharacterDao
    private lateinit var typeDao:TypeDao
    private lateinit var weightCargoService: WeightCargoService

    private var map = mutableMapOf<String, CalcUnit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightBinding.inflate(layoutInflater)
        binding.typeTextView1.text = binding.root.context.getString(R.string.type1)
        binding.typeTextView2.text = binding.root.context.getString(R.string.type2)
        db = AppDatabase.getInstance(binding.root.context)!!
        characterDao = db.characterDao()!!
        typeDao = db.typeDao()!!

        CoroutineScope(Dispatchers.Main).launch {
            getLongCalcs()
            weightCargoService = WeightCargoService(binding.root.context,
                map[resources.getString(R.string.Weight_V)]!!,
                map[resources.getString(R.string.Weight_K)]!!,
                map[resources.getString(R.string.Weight_V1)]!!,
                map[resources.getString(R.string.Weight_V2)]!!,
                map[resources.getString(R.string.Weight_Kp)]!!,
                map[resources.getString(R.string.Weight_k1)]!!,
                map[resources.getString(R.string.Weight_k2)]!!,
                map[resources.getString(R.string.Weight_Pb)]!!,
                map[resources.getString(R.string.Weight_Pc)]!!,
            )
            init()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init(){
        binding.apply {
            rcViewCargoWeight.layoutManager = LinearLayoutManager(context)
            rcViewCargoWeight.adapter = adapter

            adapter.addAll(calcUnits)

            rcViewCargoWeight1.layoutManager = LinearLayoutManager(context)
            rcViewCargoWeight1.adapter = adapter1

            adapter1.addAll(calcUnits1)
        }
    }

    override fun onClick() {
        weightCargoService.calculate()
        weightCargoService.print()

        adapter.updateOutputValue()
        adapter1.updateOutputValue()
    }

    private suspend fun getLongCalcs() = withContext(Dispatchers.IO){
        val characters: ArrayList<Character> = ArrayList()
        val characters1: ArrayList<Character> = ArrayList()
        if(myGlobalParameters?.globalSave == null){

            characters.addAll(characterDao.getAllByTypeIdFk(typeDao.getByName(resources.getString(R.string.type1)).id!!))
            for (item in characters){
                if(item.measuredIn != "") calcUnits.add(CalcUnit.fromCharacters(item))
            }
            var count = 0
            for (i in characters.indices){
                if(characters[i].measuredIn.isNotEmpty()) {
                    map[characters[i].name] = calcUnits[count++]
                }
                else map[characters[i].name] = CalcUnit.fromCharacters(characters[i])
            }

            characters1.addAll(characterDao.getAllByTypeIdFk(typeDao.getByName(resources.getString(R.string.type2)).id!!))
            for (item in characters1){
                if(item.measuredIn != "") calcUnits1.add(CalcUnit.fromCharacters(item))
            }
            count = 0
            for (i in characters1.indices){
                if(characters1[i].measuredIn.isNotEmpty()) {
                    map[characters1[i].name] = calcUnits1[count++]
                }
                else map[characters1[i].name] = CalcUnit.fromCharacters(characters1[i])
            }
        }
    }
}
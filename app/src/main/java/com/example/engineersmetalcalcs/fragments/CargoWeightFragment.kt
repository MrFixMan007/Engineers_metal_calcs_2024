package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineersmetalcalcs.adapters.LongCalcAdapter
import com.example.engineersmetalcalcs.databinding.FragmentCargoWeightBinding
import com.example.engineersmetalcalcs.listItem.CalcUnit


class CargoWeightFragment : Fragment(), LongCalcAdapter.Listener {
    private lateinit var binding: FragmentCargoWeightBinding

    private val adapter = LongCalcAdapter(this)
    private val adapter1 = LongCalcAdapter(this)

    private lateinit var calcUnits: List<CalcUnit>
    private lateinit var calcUnits1: List<CalcUnit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightBinding.inflate(layoutInflater)
        calcUnits = getLongCalcs()
        calcUnits1 = getLongCalcs1()
        init()
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

    override fun onClick(item: CalcUnit) {
        Log.i("LongCalc", item.toString())
    }

    private fun getLongCalcs(): List<CalcUnit>{
        val itemsArray = resources.getStringArray(com.example.engineersmetalcalcs.R.array.weightCargoArrayWith)

        val list: ArrayList<CalcUnit> = ArrayList()
        list.add(CalcUnit(itemsArray[0], 0f, getString(com.example.engineersmetalcalcs.R.string.m3)))
        list.add(CalcUnit(itemsArray[1], 0f, "", CalcUnit.INPUT_COEFICIENT))
        list.add(CalcUnit(itemsArray[2], 0f, getString(com.example.engineersmetalcalcs.R.string.t), CalcUnit.OUTPUT))

        return list
    }

    private fun getLongCalcs1(): List<CalcUnit>{
        val itemsArray = resources.getStringArray(com.example.engineersmetalcalcs.R.array.weightCargoArrayWithout)

        val list: ArrayList<CalcUnit> = ArrayList()
        list.add(CalcUnit(itemsArray[0], 0f, getString(com.example.engineersmetalcalcs.R.string.m3)))
        list.add(CalcUnit(itemsArray[1], 0f, getString(com.example.engineersmetalcalcs.R.string.m3)))
        list.add(CalcUnit(itemsArray[2], 0f, "", CalcUnit.INPUT_COEFICIENT))
        list.add(CalcUnit(itemsArray[3], 0f, "", CalcUnit.INPUT_COEFICIENT))
        list.add(CalcUnit(itemsArray[4], 0f, "", CalcUnit.INPUT_COEFICIENT))
        list.add(CalcUnit(itemsArray[5], 0f, getString(com.example.engineersmetalcalcs.R.string.t), CalcUnit.OUTPUT))

        return list
    }
}
package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineersmetalcalcs.adapters.LongCalcAdapter
import com.example.engineersmetalcalcs.databinding.FragmentCargoWeightBinding
import com.example.engineersmetalcalcs.listItem.CalcUnit
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.saveServices.SaveServiceCargoWeight
import com.example.engineersmetalcalcs.calcServices.CalcServiceCargoWeight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CargoWeightFragment : Fragment(), LongCalcAdapter.Listener {
    private lateinit var binding: FragmentCargoWeightBinding

    private val adapter = LongCalcAdapter(this)
    private val adapter1 = LongCalcAdapter(this)

    private var calcUnits: ArrayList<CalcUnit> = ArrayList()
    private var calcUnits1: ArrayList<CalcUnit> = ArrayList()

    private lateinit var calcServiceCargoWeight: CalcServiceCargoWeight

    private lateinit var saveServiceCargoWeight: SaveServiceCargoWeight
    private lateinit var saveServiceCargoWeight1: SaveServiceCargoWeight

    private var map = mutableMapOf<String, CalcUnit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightBinding.inflate(layoutInflater)

        binding.typeTextView1.text = binding.root.context.getString(R.string.type1)
        binding.typeTextView2.text = binding.root.context.getString(R.string.type2)

        CoroutineScope(Dispatchers.Main).launch {
            saveServiceCargoWeight = SaveServiceCargoWeight(calcUnits, map, binding.root.context, resources.getString(R.string.type1))
            saveServiceCargoWeight1 = SaveServiceCargoWeight(calcUnits1, map, binding.root.context, resources.getString(R.string.type2))

            saveServiceCargoWeight.get()
            saveServiceCargoWeight1.get()

            calcServiceCargoWeight = CalcServiceCargoWeight(binding.root.context,
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
        calcServiceCargoWeight.calculate()
        calcServiceCargoWeight.print()

        adapter.updateOutputValue()
        adapter1.updateOutputValue()
    }
}
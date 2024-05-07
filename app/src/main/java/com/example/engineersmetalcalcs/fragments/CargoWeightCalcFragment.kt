package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.adapters.LongCalcAdapter
import com.example.engineersmetalcalcs.calcServices.CalcServiceCargoWeight
import com.example.engineersmetalcalcs.databinding.FragmentCargoWeightCalcBinding
import com.example.engineersmetalcalcs.listItem.CalcUnit
import com.example.engineersmetalcalcs.saveServices.SaveServiceCargoWeight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CargoWeightCalcFragment(private val adapter: LongCalcAdapter,
                              private val adapter1: LongCalcAdapter,
                              private val calcUnits: ArrayList<CalcUnit>,
                              private val calcUnits1: ArrayList<CalcUnit>) : Fragment() {

    private lateinit var binding: FragmentCargoWeightCalcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightCalcBinding.inflate(layoutInflater)

        CoroutineScope(Dispatchers.IO).launch {
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

    private suspend fun init() = withContext(Dispatchers.Main){
        binding.apply {
            typeTextView1.text = binding.root.context.getString(R.string.type1)
            typeTextView2.text = binding.root.context.getString(R.string.type2)

            rcViewCargoWeight.layoutManager = LinearLayoutManager(context)
            rcViewCargoWeight.adapter = adapter

            adapter.addAll(calcUnits)

            rcViewCargoWeight1.layoutManager = LinearLayoutManager(context)
            rcViewCargoWeight1.adapter = adapter1

            adapter1.addAll(calcUnits1)
        }
    }
}
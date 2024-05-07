package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.engineersmetalcalcs.MyGlobalParameters
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.adapters.LongCalcAdapter
import com.example.engineersmetalcalcs.adapters.VpAdapter
import com.example.engineersmetalcalcs.calcServices.CalcServiceCargoWeight
import com.example.engineersmetalcalcs.databinding.FragmentCargoWeightBinding
import com.example.engineersmetalcalcs.listItem.CalcUnit
import com.example.engineersmetalcalcs.saveServices.SaveServiceCargoWeight
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CargoWeightFragment : Fragment(), MenuProvider, LongCalcAdapter.Listener {
    private lateinit var binding: FragmentCargoWeightBinding
    private lateinit var controller: NavController

    private val adapter = LongCalcAdapter(this)
    private val adapter1 = LongCalcAdapter(this)

    private var calcUnits: ArrayList<CalcUnit> = ArrayList()
    private var calcUnits1: ArrayList<CalcUnit> = ArrayList()

    private lateinit var calcServiceCargoWeight: CalcServiceCargoWeight

    private lateinit var saveServiceCargoWeight: SaveServiceCargoWeight

    private var map = mutableMapOf<String, CalcUnit>()

    private lateinit var fragmentList: List<Fragment>
    private lateinit var vpAdapter: VpAdapter
    private lateinit var titles: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightBinding.inflate(layoutInflater)

        titles = listOf(resources.getString(R.string.calc), resources.getString(R.string.schemes))
        controller = findNavController()

        CoroutineScope(Dispatchers.IO).launch {
            saveServiceCargoWeight = SaveServiceCargoWeight(calcUnits, calcUnits1, map, binding.root.context)

            saveServiceCargoWeight.get()

            fragmentList = listOf(CargoWeightCalcFragment(adapter, adapter1, calcUnits, calcUnits1), CargoWeightAboutFragment())

            calcServiceCargoWeight = CalcServiceCargoWeight(
                binding.root.context,
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

    private suspend fun init() = withContext(Dispatchers.Main){
        binding.apply {
            if (!MyGlobalParameters.saveIsNull()) {
                title.text = MyGlobalParameters.getSaveName()
                title.visibility = VISIBLE
            }

            vpAdapter = VpAdapter(requireActivity(), fragmentList)
            vPager2.adapter = vpAdapter
            vPager2.isSaveEnabled = false
            TabLayoutMediator(tabLayout, vPager2){
                tab, pos -> tab.text = titles[pos]
            }.attach()
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
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_calc_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.save -> save()
            android.R.id.home -> findNavController().popBackStack()
        }
        return true
    }

    override fun onClick() {
        calcServiceCargoWeight.calculate()
        calcServiceCargoWeight.print()

        adapter.updateOutputValue()
        adapter1.updateOutputValue()
    }

    fun save() {
        val nameBuilder = AlertDialog.Builder(binding.root.context)
        nameBuilder.setTitle(R.string.name_item_window)
        val nameInput = EditText(binding.root.context)
        nameInput.inputType = InputType.TYPE_CLASS_TEXT
        nameBuilder.setView(nameInput)

        nameBuilder.setPositiveButton(
            R.string.ready
        ) { _, _ ->
            val descriptionBuilder = AlertDialog.Builder(binding.root.context)
            descriptionBuilder.setTitle(R.string.description_item_window)
            val descriptionInput = EditText(binding.root.context)
            descriptionInput.inputType = InputType.TYPE_CLASS_TEXT
            descriptionBuilder.setView(descriptionInput)

            descriptionBuilder.setPositiveButton(
                R.string.ready
            ) { _, _ ->
                CoroutineScope(Dispatchers.Main).launch {
                    if(saveServiceCargoWeight.set(nameInput.text.toString(), descriptionInput.text.toString())){
                        Toast.makeText(context, resources.getString(R.string.saved), Toast.LENGTH_SHORT).show()
                    }
                    else Toast.makeText(context, resources.getString(R.string.not_saved), Toast.LENGTH_SHORT).show()
                }
            }

            descriptionBuilder.setNegativeButton(
                R.string.cancel
            ) { _, _ -> }
            descriptionBuilder.show()
        }

        nameBuilder.setNegativeButton(
            R.string.cancel
        ) { _, _ -> }
        nameBuilder.show()
    }
}
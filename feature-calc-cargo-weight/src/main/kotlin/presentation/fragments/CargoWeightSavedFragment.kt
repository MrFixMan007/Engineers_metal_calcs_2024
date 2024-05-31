package presentation.fragments

import GlobalParameter
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import metalcalcs.core_ui.R
import metalcalcs.feature_calc_cargo_weight.databinding.FragmentCargoWeightSavedBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import presentation.adapters.LongCalcAdapter
import presentation.adapters.VpAdapter
import presentation.adapters.event.AdapterEvent
import presentation.adapters.event.TypeEvent
import presentation.mapper.StringResourceMapper
import presentation.model.CalcUnit
import presentation.viewmodel.CalcWeightWithRods
import presentation.viewmodel.CalcWeightWithoutRods
import presentation.viewmodel.CargoWeightSavedViewModel
import presentation.viewmodel.ChangeUnitOfMeasureWithRods
import presentation.viewmodel.ChangeUnitOfMeasureWithoutRods
import presentation.viewmodel.SaveCalcEvent
import presentation.viewmodel.SetSavedCalc

class CargoWeightSavedFragment : Fragment(), LongCalcAdapter.Listener, MenuProvider {

    private lateinit var binding: FragmentCargoWeightSavedBinding

    private val vm : CargoWeightSavedViewModel by viewModel()

    private lateinit var adapter1 : LongCalcAdapter
    private lateinit var adapter2 : LongCalcAdapter

    private val calcUnits1 : List<CalcUnit> by inject(named("calcUnits1 saved"))
    private val calcUnits2 : List<CalcUnit> by inject(named("calcUnits2 saved"))

    private lateinit var fragmentList : List<Fragment>
    private lateinit var vpAdapter: VpAdapter
    private lateinit var titles: List<String>

    private val rcViewId1 = 0
    private val rcViewId2 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightSavedBinding.inflate(layoutInflater)

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
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_calc_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        Log.i("onMenuItemSelected fragment", this@CargoWeightSavedFragment.toString())
        when(menuItem.itemId){
            R.id.save -> save()
            android.R.id.home -> findNavController().popBackStack()
        }
        return true
    }

    private fun save() {
        val builder = AlertDialog.Builder(context)

        val customLayout: View = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
        builder.setView(customLayout)

        val nameInput = customLayout.findViewById<EditText>(R.id.nameOfSave)
        val descriptionInput = customLayout.findViewById<EditText>(R.id.descriptionOfSave)

        builder
            .setPositiveButton(R.string.ready)
            { _, _ ->
                CoroutineScope(Dispatchers.Main).launch {
                    val saveCalcEvent = SaveCalcEvent(
                        name = nameInput.text.toString(),
                        description = descriptionInput.text.toString()
                    )
                    if (vm.send(saveCalcEvent))
                    {
                        Toast.makeText(context, resources.getString(R.string.saved), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, resources.getString(R.string.not_saved), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
            .show()
    }

    private suspend fun init() = withContext(Dispatchers.Main){
        val mapper : StringResourceMapper = get()
        mapper.setValues(get(named("cargoWeight")))
        
        adapter1 = LongCalcAdapter(listener = this@CargoWeightSavedFragment, stringResourceMapper = mapper, recyclerViewId = rcViewId1)
        adapter2 = LongCalcAdapter(listener = this@CargoWeightSavedFragment, stringResourceMapper = mapper, recyclerViewId = rcViewId2)

        fragmentList = listOf(
            CargoWeightCalcFragment(
                adapter1 = adapter1,
                adapter2 = adapter2,
                calcUnits1 = calcUnits1,
                calcUnits2 = calcUnits2),
            CargoWeightAboutFragment())

        vpAdapter = VpAdapter(requireActivity(), fragmentList)
        titles = listOf(
            resources.getString(R.string.calc),
            resources.getString(R.string.schemes),
        )

        binding.apply {
            if(!GlobalParameter.saveIsEmpty()){
                val calcSave = GlobalParameter.getCalcSave()!!
                vm.send(SetSavedCalc(calcSave))
            }
            title.text = arguments?.getString("title")

            vPager2.adapter = vpAdapter
            vPager2.isSaveEnabled = false
            TabLayoutMediator(tabLayout, vPager2){
                    tab, pos -> tab.text = titles[pos]
            }.attach()
        }
    }

    override fun onClick(recyclerViewId: AdapterEvent) {
        if (recyclerViewId.rcViewId == rcViewId1){
            if (recyclerViewId.typeEvent == TypeEvent.TextChanged){
                CoroutineScope(Dispatchers.Main).launch {
                    vm.send(CalcWeightWithoutRods())
                    adapter1.updateOutputValue()
                }
            }
            else if (recyclerViewId.typeEvent == TypeEvent.MeasureChanged){
                CoroutineScope(Dispatchers.Main).launch {
                    vm.send(ChangeUnitOfMeasureWithoutRods())
                    adapter1.updateOutputValue()
                }
            }
        }
        else if (recyclerViewId.rcViewId == rcViewId2){
            if (recyclerViewId.typeEvent == TypeEvent.TextChanged){
                CoroutineScope(Dispatchers.Main).launch {
                    vm.send(CalcWeightWithRods())
                    adapter2.updateOutputValue()
                }
            }
            else if (recyclerViewId.typeEvent == TypeEvent.MeasureChanged){
                CoroutineScope(Dispatchers.Main).launch {
                    vm.send(ChangeUnitOfMeasureWithRods())
                    adapter2.updateOutputValue()
                }
            }
        }
    }

}
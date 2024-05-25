package presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
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
import metalcalcs.core_ui.R
import metalcalcs.feature_calc_cargo_weight.databinding.FragmentCargoWeightBinding
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
import presentation.viewmodel.CargoWeightViewModel
import presentation.viewmodel.ChangeUnitOfMeasureWithRods
import presentation.viewmodel.ChangeUnitOfMeasureWithoutRods
import presentation.viewmodel.SaveCalcEvent

class CargoWeightFragment : Fragment(), LongCalcAdapter.Listener, MenuProvider {

    private lateinit var binding: FragmentCargoWeightBinding

    private val vm : CargoWeightViewModel by viewModel()

    private lateinit var adapter1 : LongCalcAdapter
    private lateinit var adapter2 : LongCalcAdapter

    private val calcUnits1 : List<CalcUnit> by inject(named("calcUnits1 non saved"))
    private val calcUnits2 : List<CalcUnit> by inject(named("calcUnits2 non saved"))

    private lateinit var fragmentList : List<Fragment>
    private lateinit var vpAdapter: VpAdapter
    private lateinit var titles: List<String>

    private val rcViewId1 = 0
    private val rcViewId2 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightBinding.inflate(layoutInflater)

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
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_calc_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        Log.i("onMenuItemSelected fragment", this@CargoWeightFragment.toString())
        when(menuItem.itemId){
            R.id.save -> save()
            android.R.id.home -> findNavController().popBackStack()
        }
        return true
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
                    if (vm.send(SaveCalcEvent(nameInput.text.toString(), descriptionInput.text.toString()))){
                        Toast.makeText(context, resources.getString(R.string.saved), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, resources.getString(R.string.not_saved), Toast.LENGTH_SHORT).show()
                    }
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

    private fun init(){
        val mapper : StringResourceMapper = get()
        mapper.setValues(get(named("cargoWeight")))

        // TODO: можно перетащить в объявление
        adapter1 = LongCalcAdapter(listener = this@CargoWeightFragment,
            stringResourceMapper = mapper, recyclerViewId = rcViewId1)
        adapter2 = LongCalcAdapter(listener = this@CargoWeightFragment,
            stringResourceMapper = mapper, recyclerViewId = rcViewId2)

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
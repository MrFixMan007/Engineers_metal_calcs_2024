package presentation.fragments

import android.app.AlertDialog
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
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import data.model.calcNamesEnum.TempLikvidusNameEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import metalcalcs.core_ui.R
import metalcalcs.feature_calc_temp_likvidus.databinding.FragmentTempLikvidusCalcBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import presentation.adapter.PercentMetalAdapter
import presentation.adapters.LongCalcAdapter
import presentation.model.CalcUnit
import presentation.model.PercentMetalModel
import presentation.viewmodel.SaveCalcEvent
import presentation.viewmodel.TempLikvidusFasonViewModel

class TempLikvidusFasonCalcFragment  : Fragment(), PercentMetalAdapter.Listener, MenuProvider {

    private lateinit var binding: FragmentTempLikvidusCalcBinding

    private val vm : TempLikvidusFasonViewModel by viewModel()
    private val percentMetalMap: MutableMap<TempLikvidusNameEnum, PercentMetalModel> by inject(named("percentMetalsFason"))

    private lateinit var adapter : PercentMetalAdapter

//    private val calcUnits1 : List<CalcUnit> by inject(named("calcUnits1 non saved"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTempLikvidusCalcBinding.inflate(layoutInflater)

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

    override fun onValueChanged(item: PercentMetalModel) {
        TODO("Not yet implemented")
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

    private fun init(){
        adapter = PercentMetalAdapter(this)
        val listPercentMetal = mutableListOf<PercentMetalModel>()
        percentMetalMap.forEach { (_, u) ->  listPercentMetal.add(u)}

        binding.apply {
            rcViewTempLikvidus.adapter = adapter
            adapter.addAll(listPercentMetal)
        }
    }

    private fun save() {
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

}
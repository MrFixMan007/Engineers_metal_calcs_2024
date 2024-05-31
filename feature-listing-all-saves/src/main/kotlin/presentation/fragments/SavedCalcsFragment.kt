package presentation.fragments

import GlobalParameter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import data.model.CalcSave
import data.model.TypeEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import metalcalcs.feature_listing_all_saves.R
import metalcalcs.feature_listing_all_saves.databinding.FragmentSavedCalcsBinding
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import presentation.adapters.SaveAdapter
import presentation.model.SaveItem
import presentation.viewmodel.SavesViewModel

class SavedCalcsFragment : Fragment(), SaveAdapter.Listener {
    private lateinit var binding: FragmentSavedCalcsBinding
    private lateinit var controller: NavController

    private val viewmodel: SavesViewModel by viewModel()
    private val saveItems = mutableListOf<SaveItem>()
    private val calcSaves = mutableListOf<CalcSave>()

    private val adapter = SaveAdapter(this, get())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSavedCalcsBinding.inflate(layoutInflater)

        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        CoroutineScope(Dispatchers.Main).launch {
            update()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()

    }

    private suspend fun update(){
        viewmodel.update()
        if (calcSaves.size != viewmodel.allCalcSaves.size){
            for (i in calcSaves.size..<viewmodel.allCalcSaves.size) {
                calcSaves.add(viewmodel.allCalcSaves[i])
                saveItems.add(
                    SaveItem(
                        name = viewmodel.allCalcSaves[i].name,
                        description = viewmodel.allCalcSaves[i].description,
                        result = viewmodel.allCalcSaves[i].result.replace(" ", "\n"),
                        date = viewmodel.allCalcSaves[i].date,
                        type = viewmodel.allCalcSaves[i].type
                ))
                adapter.add(saveItems.last())
            }
        }
    }

    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(context)
            rcView.adapter = adapter
        }
    }

    override fun onClick(item: SaveItem) {
        GlobalParameter.setCalcSave(calcSaves[saveItems.indexOf(item)])
        when (item.type.typeEnum){
            TypeEnum.WeightOfCargo -> controller.navigate(R.id.action_savedCalcsFragment_to_cargo_weight_nav, bundleOf("title" to item.name))
            TypeEnum.TemperatureFason -> controller.navigate(R.id.action_savedCalcsFragment_to_fason_saved_nav, bundleOf("title" to item.name))
            TypeEnum.TemperatureIngot -> controller.navigate(R.id.action_savedCalcsFragment_to_ingot_saved_nav, bundleOf("title" to item.name))
        }

    }

    override fun deleteSave(item: SaveItem) {
        adapter.deleteItem(item)
        CoroutineScope(Dispatchers.Main).launch {
            val saveForRemove = calcSaves.find { it.name == item.name && it.date == item.date }
            calcSaves.remove(saveForRemove)
            saveItems.remove(item)
        }
    }

    override fun itemWasChanged(item: SaveItem) {
        adapter.itemWasChanged(item)
    }

}
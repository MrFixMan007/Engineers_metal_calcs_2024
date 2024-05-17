package presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import metalcalcs.feature_listing_all_saves.R
import metalcalcs.feature_listing_all_saves.databinding.FragmentSavedCalcsBinding
import presentation.adapters.SaveAdapter
import presentation.model.SaveItem

class SavedCalcsFragment : Fragment(), SaveAdapter.Listener {
    private lateinit var binding: FragmentSavedCalcsBinding
    private lateinit var controller: NavController

    private val adapter = SaveAdapter(this)
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()
    }

    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(context)
            rcView.adapter = adapter
        }
    }

    override fun onClick(item: SaveItem) {
        controller.navigate(R.id.action_savedCalcsFragment_to_cargo_weight_nav)
    }

    override fun deleteSave(item: SaveItem) {
        adapter.deleteItem(item)
        CoroutineScope(Dispatchers.Main).launch {
            //TODO: удаление через репу
        }
    }

    override fun itemWasChanged(item: SaveItem) {
        adapter.itemWasChanged(item)
//        CoroutineScope(Dispatchers.Main).launch {
//            // я хз чо это. мб имя или описание было изменено и это упоминание
//        }
    }

}
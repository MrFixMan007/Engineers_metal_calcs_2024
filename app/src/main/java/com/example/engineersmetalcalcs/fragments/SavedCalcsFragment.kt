package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineersmetalcalcs.MyGlobalParameters
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.adapters.SavesAdapter
import com.example.engineersmetalcalcs.databinding.FragmentSavedCalcsBinding
import com.example.engineersmetalcalcs.listItem.SaveItem
import com.example.engineersmetalcalcs.saveServices.SaveServiceListOfSaves
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedCalcsFragment : Fragment(), SaveServiceListOfSaves.Listener, SavesAdapter.Listener {
    private lateinit var binding: FragmentSavedCalcsBinding
    private lateinit var saveServiceListOfSaves: SaveServiceListOfSaves
    private lateinit var controller: NavController

    private val adapter = SavesAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSavedCalcsBinding.inflate(layoutInflater)

        CoroutineScope(Dispatchers.IO).launch {
            saveServiceListOfSaves = SaveServiceListOfSaves(binding.root.context, this@SavedCalcsFragment)
            init()
            saveServiceListOfSaves.get()
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
        controller = findNavController()
    }

    private suspend fun init() = withContext(Dispatchers.Main){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(context)
            rcView.adapter = adapter
        }
    }

    override suspend fun setSaves(saves: List<SaveItem>): Unit = withContext(Dispatchers.Main) {
        adapter.addAll(saves)
    }

    override fun onClick(item: SaveItem) {
        MyGlobalParameters.setSave(item.save)
        controller.navigate(R.id.action_savedCalcsFragment_to_cargoWeightFragment)
    }

    override fun deleteSave(item: SaveItem) {
        adapter.deleteItem(item)
        CoroutineScope(Dispatchers.Main).launch {
            saveServiceListOfSaves.deleteSave(item)
        }
    }

    override fun itemWasChanged(item: SaveItem) {
        adapter.itemWasChanged(item)
        CoroutineScope(Dispatchers.Main).launch {
            saveServiceListOfSaves.saveWasChanged(item)
        }
    }

}
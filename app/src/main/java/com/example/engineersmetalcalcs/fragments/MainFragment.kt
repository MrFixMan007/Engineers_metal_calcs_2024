package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import com.example.engineersmetalcalcs.MainActivity
import com.example.engineersmetalcalcs.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(), MenuProvider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
        val controller = findNavController()
        val button1 = view.findViewById<Button>(R.id.main_menu_button1)
        val button2 = view.findViewById<Button>(R.id.main_menu_button2)

        button1.setOnClickListener {controller.navigate(R.id.action_mainFragment_to_likvidTempMenuFragment)}
        button2.setOnClickListener {controller.navigate(R.id.action_mainFragment_to_castingMenuFragment)}
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.turnOff -> activity?.finish() //TODO: закрывает, но на фоне остается
        }
        return true
    }
}
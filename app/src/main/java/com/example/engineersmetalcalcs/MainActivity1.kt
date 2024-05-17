package com.example.engineersmetalcalcs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.engineersmetalcalcs.databinding.ActivityMain1Binding

class MainActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivityMain1Binding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        controller = findNavController(R.id.fragmentContainerView1)

        binding.bottomNavigationView.setupWithNavController(controller)

        setupActionBarWithNavController(controller)
    }
    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp() || super.onSupportNavigateUp()
    }

}
package com.example.engineersmetalcalcs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.engineersmetalcalcs.databinding.ActivityMainBinding
import com.example.engineersmetalcalcs.db.AppDatabase
import com.example.engineersmetalcalcs.fragments.GuideFragment
import com.example.engineersmetalcalcs.fragments.MainFragment
import com.example.engineersmetalcalcs.fragments.SavedCalcsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController
    private val mapOfFragments = mutableMapOf<String, Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//      this@MainActivity.deleteDatabase("database")
        AppDatabase.getInstance(binding.root.context)

        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        controller = findNavController(R.id.fragmentContainerView)

        binding.bottomNavigationView.setupWithNavController(controller)

        setupActionBarWithNavController(controller)
    }
    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp() || super.onSupportNavigateUp()
    }

}
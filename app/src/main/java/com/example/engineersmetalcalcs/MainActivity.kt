package com.example.engineersmetalcalcs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.engineersmetalcalcs.databinding.ActivityMainBinding
import com.example.engineersmetalcalcs.db.AppDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // this.deleteDatabase("database")
        AppDatabase.getInstance(this)

        MyGlobalParameters.getInstance()
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))
    }
    override fun onSupportNavigateUp(): Boolean {
        val controller = findNavController(R.id.fragmentContainerView)
        return controller.navigateUp() || super.onSupportNavigateUp()
    }
}
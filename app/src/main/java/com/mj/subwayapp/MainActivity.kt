package com.mj.subwayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.mj.subwayapp.databinding.ActivityMainBinding
import com.mj.subwayapp.extension.toGone
import com.mj.subwayapp.extension.toVisible

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        bindViews()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navigationController)
    }

    private fun bindViews() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.station_arrivals_dest) {
                binding.toolbar.toVisible()
            } else {
                binding.toolbar.toGone()
            }
        }
    }
}
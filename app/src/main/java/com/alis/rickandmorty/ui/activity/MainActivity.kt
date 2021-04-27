package com.alis.rickandmorty.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alis.rickandmorty.R
import com.alis.rickandmorty.databinding.ActivityMainBinding
import com.alis.rickandmorty.datastore.DataStorePreferences
import com.alis.rickandmorty.extensions.gone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()

        /* TODO
        lifecycleScope.launch {
            dataStorePreferences.getExampleCounter().collect {
                Log.d("anime", it.toString())
            }
        }

        lifecycleScope.launch {
            dataStorePreferences.incrementExampleCounter(2)
        }
        */
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateUIBottomNavigation(destination.id)
        }
    }

    private fun updateUIBottomNavigation(id: Int) {
        when (id) {
            R.id.detailFragment -> {
                binding.bottomNavigation.gone()
            }
        }
    }
}
package com.alis.rickandmorty.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.alis.rickandmorty.R
import com.alis.rickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationItemReselectListener: OnBottomNavigationItemReselect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupToolbar()
        setupBottomNavigation()
        updateUIComponents()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_characters,
                R.id.navigation_locations,
                R.id.navigation_episodes,
                R.id.navigation_search
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.apply {
            itemIconTintList = null
            setupWithNavController(navController)

            setOnNavigationItemReselectedListener {
                when (it.itemId) {
                    R.id.navigation_characters,
                    R.id.navigation_locations,
                    R.id.navigation_episodes -> {
                        bottomNavigationItemReselectListener.onItemReselect()
                    }
                }
            }
        }
    }

    private fun updateUIComponents() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.characterDetailFragment,
                R.id.locationDetailFragment,
                R.id.episodeDetailFragment
                -> {
                    //TODO: scroll up bottom navigation and toolbar, because their hided when scroll and open detail not showing
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    fun interface OnBottomNavigationItemReselect {
        fun onItemReselect()
    }

    fun setOnBottomNavigationItemReselectListener(bottomNavigationItemReselectListener: OnBottomNavigationItemReselect) {
        this.bottomNavigationItemReselectListener = bottomNavigationItemReselectListener
    }
}
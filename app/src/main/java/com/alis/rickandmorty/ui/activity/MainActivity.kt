package com.alis.rickandmorty.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.alis.rickandmorty.R
import com.alis.rickandmorty.databinding.ActivityMainBinding
import com.alis.rickandmorty.extensions.gone
import com.alis.rickandmorty.extensions.visible
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

        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateUIComponents(destination.id)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_characters,
                R.id.navigation_locations,
                R.id.navigation_episodes
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

    private fun updateUIComponents(id: Int) {
        binding.apply {
            // region BottomNavigation
            when (id) {
                R.id.detailFragment -> {
                    bottomNavigation.gone()
                }
                else -> {
                    bottomNavigation.visible()
                }
            }
            // endregion
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return true
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
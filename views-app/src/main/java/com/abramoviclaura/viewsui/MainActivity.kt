package com.abramoviclaura.viewsui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.abramoviclaura.shared.screen.LogTag
import com.abramoviclaura.shared.screen.logMillis
import com.abramoviclaura.views_app.R
import com.abramoviclaura.views_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomBar.setupWithNavController(navController)

        binding.bottomBar.setOnItemSelectedListener {
            logMillis(LogTag.NAVIGATION_TIME) {
                when (it.itemId) {
                    R.id.item_greetings -> NavigationUI.onNavDestinationSelected(it, navController)
                    R.id.item_list -> NavigationUI.onNavDestinationSelected(it, navController)
                    R.id.item_basketballs -> NavigationUI.onNavDestinationSelected(it, navController)
                    else -> throw IllegalStateException("No such item: ${it.itemId}")
                }
            }

            true
        }
    }
}



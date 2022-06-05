package ru.owepkovr.freetoplaygames.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import freetoplaygames.R
import freetoplaygames.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity(R.layout.activity_main), NavController.OnDestinationChangedListener {

    private val binding: ActivityMainBinding by viewBinding(R.id.activity_main)
    private val viewModel: MainViewModel by viewModel()
    private val navController: NavController get() = findNavController(R.id.mainNavHost)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initNavigation()
    }

    private fun initNavigation() {
        binding.navigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        TODO("Not yet implemented")
    }
}
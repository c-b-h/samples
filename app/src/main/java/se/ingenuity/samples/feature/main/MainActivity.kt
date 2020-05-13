package se.ingenuity.samples.feature.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import se.ingenuity.samples.R
import se.ingenuity.samples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity__main
        )
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            binding.navView.menu.children.map(MenuItem::getItemId).toSet(),
            binding.drawer
        )

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.navView.setupWithNavController(navController)
        binding.toolbarContainer.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
    }
}

package com.example.jokenpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jokenpo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val toolbar = binding.toolbar2

        setContentView(binding.root)
        setSupportActionBar(toolbar)

        drawer = binding.root
        navDrawer = binding.navigationView
        bottomNav = binding.bottomNav

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.playerFragment,R.id.resultFragment), drawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navDrawer.setupWithNavController(navController)

        configureListeners()

    }




    private fun configureListeners() {
        /*navDrawer.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            when (menuItem.itemId) {
                R.id.drawer_home -> {
                    onBackPressed()
                    true
                }
                else -> false
            }
        }*/

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_option_1 -> {
                    Snackbar.make(this, drawer, getString(R.string.bottom_title_1), Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_option_2 -> {
                    Snackbar.make(this, drawer, getString(R.string.bottom_title_2), Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    Snackbar.make(this,drawer, getString(R.string.something_went_wrong),Snackbar.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.second_activity_menu, menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_save -> {
                Snackbar.make(this, drawer, getString(R.string.menu_save), Snackbar.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.menu_settings -> {
                Snackbar.make(
                    this,
                    drawer,
                    getString(R.string.menu_settings),
                    Snackbar.LENGTH_SHORT
                ).show()
                true
            }
            else -> {
                Snackbar.make(
                    this,
                    drawer,
                    getString(R.string.something_went_wrong),
                    Snackbar.LENGTH_SHORT
                ).show()
                false
            }
        }
    }*/

    override fun onSupportNavigateUp(): Boolean {
        drawer.openDrawer(GravityCompat.START)
        return true
    }

}
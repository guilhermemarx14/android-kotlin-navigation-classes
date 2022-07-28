package com.example.jokenpo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.jokenpo.databinding.ActivitySecondBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivitySecondBinding
    private lateinit var navDrawer: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureBinding()
        setupToolbar()

        binding.textSample.text = getTextFromIntent()

        configureListeners()

    }

    private fun configureBinding() {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        drawer = binding.root
        setContentView(drawer)

        navDrawer = binding.navigationView
    }

    private fun configureListeners() {
        binding.goBackButton.setOnClickListener {
            val result = Intent()
            result.putExtra("result", "Ok")
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        navDrawer.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            when (menuItem.itemId) {
                R.id.drawer_home -> {
                    onBackPressed()
                    true
                }
                else -> false
            }
        }
    }

    private fun getTextFromIntent(): String {
        return if (intent?.extras?.isEmpty == false) "${intent.getStringExtra("name")}: ${
            intent.getIntExtra(
                "age",
                0
            )
        }" else "Error"
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.second_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
    }

    override fun onSupportNavigateUp(): Boolean {
        drawer.openDrawer(GravityCompat.START)
        return true
    }

}
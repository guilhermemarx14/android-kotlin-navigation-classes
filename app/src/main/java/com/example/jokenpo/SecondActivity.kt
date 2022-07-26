package com.example.jokenpo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.jokenpo.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        root = binding.root

        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        println(intent.getStringExtra("name"))
        println(
            intent.getIntExtra(
                "age",
                0
            )
        )

        binding.textSample.text =
            if (intent?.extras?.isEmpty == false) "${intent.getStringExtra("name")}: ${
                intent.getIntExtra(
                    "age",
                    0
                )
            }" else "Error"

        binding.goBackButton.setOnClickListener {
            val result = Intent()
            result.putExtra("result", "Ok")
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.second_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_save -> {
                Snackbar.make(this, root, getString(R.string.menu_save), Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.menu_settings -> {
                Snackbar.make(this, root, getString(R.string.menu_settings), Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> {
                Snackbar.make(this, root, getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT).show()
                false
            }
        }
    }
}
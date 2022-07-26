package com.example.jokenpo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.jokenpo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.minha_app_bar)
        supportActionBar?.setLogo(R.drawable.ic_logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val secondActivityIntent = Intent(this, SecondActivity::class.java)
        secondActivityIntent.putExtra("name", "Guilherme")
        secondActivityIntent.putExtra("age", 26)

        val activityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    if (intent?.hasExtra("result") == true)
                        Snackbar.make(binding.root, intent.getStringExtra("result") ?: "Error", Snackbar.LENGTH_SHORT).show()
                }
            }

        binding.goToSecondActivityButton.setOnClickListener {
            activityResult.launch(secondActivityIntent)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
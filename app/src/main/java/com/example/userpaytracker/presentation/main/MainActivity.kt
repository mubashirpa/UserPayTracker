package com.example.userpaytracker.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.userpaytracker.databinding.ActivityMainBinding
import com.example.userpaytracker.navigation.findNavController
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DynamicColors.applyToActivityIfAvailable(this)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findNavController(binding.navHostFragment.id)
    }
}

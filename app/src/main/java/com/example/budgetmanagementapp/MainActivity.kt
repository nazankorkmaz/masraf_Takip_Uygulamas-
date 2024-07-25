package com.example.budgetmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.budgetmanagementapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        
        val view = binding.root
        setContentView(view)
    //    setContentView(R.layout.activity_main)
    }
}
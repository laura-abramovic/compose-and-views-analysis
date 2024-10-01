package com.abramoviclaura.viewsui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abramoviclaura.views_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

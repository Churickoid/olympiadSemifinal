package com.example.olympiadsemifinal.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olympiadsemifinal.R
import com.example.olympiadsemifinal.databinding.ActivityMainBinding
import com.example.olympiadsemifinal.presentation.screen.list.ListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, ListFragment())
                .commit()
        }

    }
}
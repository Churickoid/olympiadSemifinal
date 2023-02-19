package com.example.olympiadsemifinal.presentation

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.olympiadsemifinal.R
import com.example.olympiadsemifinal.databinding.ActivityMainBinding
import com.example.olympiadsemifinal.domain.model.Service
import com.example.olympiadsemifinal.presentation.contract.Navigation
import com.example.olympiadsemifinal.presentation.screen.info.InfoFragment
import com.example.olympiadsemifinal.presentation.screen.list.ListFragment

class MainActivity : AppCompatActivity(), Navigation {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Проекты ВК"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, ListFragment())
                .commit()
        }
        onBackPressedDispatcher.addCallback(this) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.title = "Проекты ВК"
                supportFragmentManager.popBackStack()
            }
            else{
                finish()
            }

        }


    }

    override fun showInfoScreen(service: Service) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.fragmentContainer, InfoFragment().newInstance(service))
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = service.name
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}
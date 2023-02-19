package com.example.olympiadsemifinal.presentation

import android.app.Application
import com.example.olympiadsemifinal.presentation.di.DependencyInjectionContainer

class App : Application() {
    val container = DependencyInjectionContainer()
}
package com.example.olympiadsemifinal.presentation.contract

import androidx.fragment.app.Fragment
import com.example.olympiadsemifinal.domain.model.Service

interface Navigation {

    fun showInfoScreen(service: Service)
}
fun Fragment.navigation(): Navigation {
    return requireActivity() as Navigation
}
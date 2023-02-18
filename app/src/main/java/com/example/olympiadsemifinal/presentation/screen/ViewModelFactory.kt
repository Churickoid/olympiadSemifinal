package com.example.olympiadsemifinal.presentation.screen

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.olympiadsemifinal.presentation.App
import com.example.olympiadsemifinal.presentation.screen.list.ListViewModel
import java.lang.IllegalStateException


class ViewModelFactory(private val app: App) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            ListViewModel :: class.java -> {
                ListViewModel(app.container.getServiceListUseCase)
            }
            else ->{
                throw IllegalStateException("Unknown View model class")
            }
        }

        return viewModel as T
    }

}
fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)
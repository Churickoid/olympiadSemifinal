package com.example.olympiadsemifinal.presentation.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.olympiadsemifinal.domain.model.Service
import com.example.olympiadsemifinal.domain.usecase.GetServiceListUseCase
import kotlinx.coroutines.launch

class ListViewModel(private val getServiceListUseCase: GetServiceListUseCase) : ViewModel() {

    private val _serviceList = MutableLiveData<List<Service>>()
    val serviceList: LiveData<List<Service>> = _serviceList

    private val _state = MutableLiveData<Int>()
    val state: LiveData<Int> = _state

    init {
        getServiceList()
    }

    fun getServiceList() {
        viewModelScope.launch {
            _state.value = STATE_LOADING
            try {
                _serviceList.value = getServiceListUseCase.invoke()
                _state.value = STATE_SUCCESS
            }catch (e: Exception){
                _state.value = STATE_ERROR
            }
        }
    }

    companion object {
        const val STATE_LOADING = 0
        const val STATE_SUCCESS = 1
        const val STATE_ERROR = 2
    }

}
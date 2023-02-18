package com.example.olympiadsemifinal.presentation.di


import com.example.olympiadsemifinal.data.ApiRequest
import com.example.olympiadsemifinal.data.ServiceRepositoryImpl
import com.example.olympiadsemifinal.domain.usecase.GetServiceListUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DependencyInjectionContainer {

    private val okHttpClient = OkHttpClient
        .Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mobile-olympiad-trajectory.hb.bizmrg.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiRequest::class.java)

    private val serviceRepository = ServiceRepositoryImpl(retrofit)

    val getServiceListUseCase = GetServiceListUseCase(serviceRepository)


}
package com.example.olympiadsemifinal.data

import com.example.olympiadsemifinal.domain.ServiceRepository
import com.example.olympiadsemifinal.domain.model.Service

class ServiceRepositoryImpl(private val retrofit: ApiRequest) : ServiceRepository {

    override suspend fun getServiceList(): List<Service> {
        val serviceListResponse = retrofit.getServiceList()
        return serviceListResponse.items
    }

}
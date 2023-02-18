package com.example.olympiadsemifinal.domain

import com.example.olympiadsemifinal.domain.model.Service

interface ServiceRepository {

    suspend fun getServiceList(): List<Service>

}
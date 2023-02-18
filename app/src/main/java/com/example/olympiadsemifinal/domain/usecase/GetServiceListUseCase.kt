package com.example.olympiadsemifinal.domain.usecase

import com.example.olympiadsemifinal.domain.ServiceRepository
import com.example.olympiadsemifinal.domain.model.Service

class GetServiceListUseCase(private val serviceRepository: ServiceRepository) {

    suspend operator fun invoke(): List<Service> {
        return serviceRepository.getServiceList()
    }
}
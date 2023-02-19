package com.example.olympiadsemifinal.domain.model

import java.io.Serializable


data class Service(
    val name: String,
    val description: String,
    val icon_url: String,
    val service_url: String
) : Serializable
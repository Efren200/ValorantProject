package com.efpaga.valorantprojectfinal.valorantapi.models

data class BaseModel<T>(
    val status: Int,
    val data: T
)
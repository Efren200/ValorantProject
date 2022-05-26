package com.efpaga.valorantprojectfinal.valorantapi.utils

import com.efpaga.valorantprojectfinal.valorantapi.exceptions.ValorantApiException
import com.efpaga.valorantprojectfinal.valorantapi.models.BaseModel
import retrofit2.Call

inline fun <reified T> Call<BaseModel<T>>.send(): BaseModel<T> {
    val response = this.execute();
    if(!response.isSuccessful) {
        throw ValorantApiException(response.code(), "Something went wrong.")
    }
    if(response.body() == null) {
        throw ValorantApiException(null, "Response is null.")
    }
    return response.body()!!
}
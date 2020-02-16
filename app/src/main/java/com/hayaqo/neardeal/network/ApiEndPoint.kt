package com.hayaqo.neardeal.network

import com.hayaqo.neardeal.model.Store
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("get_all_stores.php")
    fun getStores() : Call<MutableList<Store>>

}
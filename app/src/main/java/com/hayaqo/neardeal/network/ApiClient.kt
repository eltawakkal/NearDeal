package com.hayaqo.neardeal.network

import com.hayaqo.neardeal.fragment.FragStores
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun getRetrofit() : Retrofit {
        var retrofit: Retrofit? = null

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2/neardeal/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }

}
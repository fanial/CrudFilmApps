package com.fal.crudfilmapps.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientUser {
    const val BASE_URL = "https://6254434289f28cf72b5aed04.mockapi.io/user/"

    val instance : RetrofitServiceUser by lazy {
        val service = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        service.create(RetrofitServiceUser::class.java)
    }
}
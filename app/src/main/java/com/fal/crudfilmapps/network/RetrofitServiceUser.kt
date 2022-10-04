package com.fal.crudfilmapps.network

import com.fal.crudfilmapps.model.ResponseDataFilmItem
import com.fal.crudfilmapps.model.ResponseDataUser
import com.fal.crudfilmapps.model.ResponseDataUserItem
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceUser {

    @GET("user")
    fun getAllUser() : Call<List<ResponseDataUserItem>>

}
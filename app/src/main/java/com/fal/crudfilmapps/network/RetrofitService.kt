package com.fal.crudfilmapps.network

import com.fal.crudfilmapps.model.DataFilm
import com.fal.crudfilmapps.model.ResponseDataFilmItem
import com.fal.crudfilmapps.model.ResponseDataUserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("film")
    fun getAll() : Call<List<ResponseDataFilmItem>>

    @POST("film")
    fun addData(@Body film : DataFilm) : Call<ResponseDataFilmItem>

    @GET("user")
    fun getAllUser() : Call<List<ResponseDataUserItem>>

    @POST("user")
    fun postUser(@Body user : ResponseDataUserItem) : Call<ResponseDataUserItem>
}
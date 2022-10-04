package com.fal.crudfilmapps.model


import com.google.gson.annotations.SerializedName

data class ResponseDataUserItem(
    @SerializedName("address")
    var address: String,
    @SerializedName("age")
    var age: Int,
    @SerializedName("id")
    var id: String,
    @SerializedName("nama")
    var nama: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("username")
    var username: String
)
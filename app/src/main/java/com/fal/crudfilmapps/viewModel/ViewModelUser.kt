package com.fal.crudfilmapps.viewModel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fal.crudfilmapps.model.ResponseDataUserItem
import com.fal.crudfilmapps.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser:ViewModel() {

    lateinit var addLiveDataUser : MutableLiveData<ResponseDataUserItem?>

    init {
        addLiveDataUser = MutableLiveData()
    }

    fun postLiveDataUser() : MutableLiveData<ResponseDataUserItem?> {
        return addLiveDataUser
    }

    fun postApiUser(alamat : String, umur : Int , id : Int, nama : String, name : String,password : String, userame : String){
        RetrofitClient.instance.postUser(ResponseDataUserItem(alamat,umur,"", nama, name,password,userame ))
            .enqueue(object : Callback<ResponseDataUserItem>{
                override fun onResponse(
                    call: Call<ResponseDataUserItem>,
                    response: Response<ResponseDataUserItem>
                ) {
                    if (response.isSuccessful){
                        addLiveDataUser.postValue(response.body())
                    }else{
                        addLiveDataUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataUserItem>, t: Throwable) {
                    addLiveDataUser.postValue(null)
                }

            })
    }

}
package com.fal.crudfilmapps.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fal.crudfilmapps.model.ResponseDataFilmItem
import com.fal.crudfilmapps.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var allData : MutableLiveData<List<ResponseDataFilmItem>>

    init {
        allData = MutableLiveData()
    }

    fun allLiveData() : MutableLiveData<List<ResponseDataFilmItem>>{
        return allData
    }

    //GET ALL Data from API
    fun callAllData(){
        RetrofitClient.instance.getAll()
            .enqueue(object : Callback<List<ResponseDataFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataFilmItem>>,
                    response: Response<List<ResponseDataFilmItem>>,
                ) {
                    if (response.isSuccessful){
                        allData.postValue(response.body())
                    }else{
                        error(response.message())
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                    allData.postValue(error(t.message.toString()))
                }

            })
    }
}
package com.fal.crudfilmapps.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fal.crudfilmapps.model.DataFilm
import com.fal.crudfilmapps.model.PutResponseFilm
import com.fal.crudfilmapps.model.ResponseDataFilmItem
import com.fal.crudfilmapps.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var allData : MutableLiveData<List<ResponseDataFilmItem>>
    lateinit var updateFilm : MutableLiveData<List<PutResponseFilm>?>
    lateinit var deleteFilm : MutableLiveData<Int?>

    init {
        allData = MutableLiveData()
        updateFilm = MutableLiveData()
        deleteFilm = MutableLiveData()
    }

    fun allLiveData() : MutableLiveData<List<ResponseDataFilmItem>>{
        return allData
    }

    fun updateLiveDataFilm() : MutableLiveData<List<PutResponseFilm>?> {
        return updateFilm
    }

    fun deleteLiveDataFIlm() : MutableLiveData<Int?> {
        return deleteFilm
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

    // Update Function to API
    fun updateApiFilm(id : Int, name : String, image : String, director : String, description : String){
        RetrofitClient.instance.updateFilm(id, DataFilm(description,director,image,name))
            .enqueue(object : Callback<List<PutResponseFilm>>{
                override fun onResponse(
                    call: Call<List<PutResponseFilm>>,
                    response: Response<List<PutResponseFilm>>
                ) {
                    if (response.isSuccessful){
                        updateFilm.postValue(response.body())
                    }else{
                        updateFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<PutResponseFilm>>, t: Throwable) {
                    updateFilm.postValue(null)
                }

            })
    }

    fun deleteApiFilm(id : Int){
        RetrofitClient.instance.deleteFilm(id)
            .enqueue(object : Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful){
                        deleteFilm.postValue(response.body())
                    }else{
                        deleteFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    deleteFilm.postValue(null)
                }

            })
    }
}
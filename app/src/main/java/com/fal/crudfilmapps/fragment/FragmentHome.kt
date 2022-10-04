package com.fal.crudfilmapps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.adapter.AdapterFilm
import com.fal.crudfilmapps.databinding.FragmentHomeBinding
import com.fal.crudfilmapps.model.ResponseDataFilmItem
import com.fal.crudfilmapps.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //show list film
        showList()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showList() {
        RetrofitClient.instance.getAll()
            .enqueue(object : Callback<List<ResponseDataFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataFilmItem>>,
                    response: Response<List<ResponseDataFilmItem>>,
                ) {
                    if (response.isSuccessful){
                        binding.rvFilm.layoutManager = GridLayoutManager(context, 3)
                        binding.rvFilm.adapter = AdapterFilm(response.body()!!)
                        Toast.makeText(context, "Load Data Success", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "Load Data Failed", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                    Toast.makeText(context, "Something Wrong", Toast.LENGTH_LONG).show()
                }

            })
    }


}
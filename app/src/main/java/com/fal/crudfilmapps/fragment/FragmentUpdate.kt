package com.fal.crudfilmapps.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.databinding.FragmentLoginBinding
import com.fal.crudfilmapps.databinding.FragmentUpdateBinding
import com.fal.crudfilmapps.viewModel.ViewModelFilm


class FragmentUpdate : Fragment() {
    lateinit var binding : FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUpdate.setOnClickListener{
            requireActivity().run {
                var fetID = arguments?.getInt("update",0)
                var name = binding.etNameFilm.text.toString()
                var image = binding.etImageFIlm.text.toString()
                var director = binding.etDirectorFilm.text.toString()
                var description = binding.etDescFilm.text.toString()
                Log.d("infoid",fetID.toString())

                updateFilm(fetID.toString().toInt(),name, image, director, description)

                Navigation.findNavController(it).navigate(R.id.action_fragmentUpdate_to_fragmentHome)

            }
        }
    }


    fun updateFilm(id : Int, name : String, image : String, director : String, description : String){
        var viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.updateApiFilm(id, name, image, director, description)
        viewModel.updateLiveDataFilm().observe(viewLifecycleOwner, Observer{
            if (it != null){
                Toast.makeText(context, "Update Data Success", Toast.LENGTH_SHORT).show()
            }
        })
    }



}
package com.fal.crudfilmapps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.databinding.FragmentAddBinding
import com.fal.crudfilmapps.model.DataFilm
import com.fal.crudfilmapps.network.RetrofitClient
import com.fal.crudfilmapps.viewModel.ViewModelFilm

class FragmentAdd : Fragment() {

    lateinit var binding : FragmentAddBinding
    lateinit var modelFilm: ViewModelFilm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvUploadFilm.setOnClickListener {
            var name = binding.etAddNameFilm.text.toString()
            var director = binding.etAddDirectorFilm.text.toString()
            var image = binding.etAddNImageFilm.text.toString()
            var desc = binding.etAddDescF.text.toString()
            addItemData(desc, director, image, name)
            findNavController().navigate(R.id.action_fragmentAdd_to_fragmentHome)
        }
    }

    fun addItemData(description : String, director : String, image : String, name : String){
        modelFilm = ViewModelProvider(this).get(ViewModelFilm::class.java)
        modelFilm.postData(description, director, image, name)
        modelFilm.addLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(context, "Add Data Success", Toast.LENGTH_LONG).show()
            }
        })
    }

}
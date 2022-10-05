package com.fal.crudfilmapps.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.databinding.FragmentDeleteDialogBinding
import com.fal.crudfilmapps.viewModel.ViewModelFilm

class DeleteFragmentDialog : Fragment() {
    lateinit var binding : FragmentDeleteDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHapus.setOnClickListener{
            requireActivity().run {
                var fetID = arguments?.getInt("delete",0)
                Log.d("infoid",fetID.toString())
                deleteFilm(fetID!!.toInt())
                Navigation.findNavController(it).navigate(R.id.action_deleteFragmentDialog_to_fragmentHome)
            }
        }

        binding.btnBatal.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_deleteFragmentDialog_to_fragmentHome)
        }
    }

    fun deleteFilm(id : Int){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.deleteApiFilm(id)
        viewModel.deleteLiveDataFIlm().observe(viewLifecycleOwner,Observer{
            if (it != null){
                Toast.makeText(context, "Update Data Success", Toast.LENGTH_SHORT).show()
                Log.d("deleteFilm", it.toString())
            }
        })
    }

}
package com.fal.crudfilmapps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.databinding.FragmentDetailBinding
import com.fal.crudfilmapps.databinding.FragmentHomeBinding


class FragmentDetail : Fragment() {

    lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var judul = arguments?.getString("judul")
        var date = arguments?.getString("date")
        var dekripsi = arguments?.getString("Dekripsi")
        var gambar = arguments?.getString("gambar")
        var id = arguments?.getInt("id")

        binding.tvjudul.text = judul
        binding.tvtahun.text = date
        binding.tvdekt.text = dekripsi
        Glide.with(this).load(gambar).into(binding.tvgambar)

        binding.btnEdit.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("update",id!!.toInt())
            Navigation.findNavController(it).navigate(R.id.action_fragmentDetail_to_fragmentUpdate,bundle)
        }

        binding.btnDelete.setOnClickListener{
            val bundle =Bundle()
            bundle.putInt("delete",id!!.toInt())
            Navigation.findNavController(it).navigate(R.id.action_fragmentDetail_to_deleteFragmentDialog,bundle)
        }
    }


}
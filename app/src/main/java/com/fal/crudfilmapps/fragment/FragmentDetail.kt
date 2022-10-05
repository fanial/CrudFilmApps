package com.fal.crudfilmapps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        var id = arguments?.getString("id")

        binding.tvjudul.text = judul
        binding.tvtahun.text = date
        binding.tvdekt.text = dekripsi
        Glide.with(this).load(gambar).into(binding.tvgambar)
    }


}
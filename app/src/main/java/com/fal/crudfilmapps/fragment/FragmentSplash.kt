package com.fal.crudfilmapps.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.databinding.FragmentLoginBinding
import com.fal.crudfilmapps.databinding.FragmentSplashBinding

class FragmentSplash : Fragment() {

    lateinit var binding : FragmentSplashBinding
    lateinit var dataUserShared : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataUserShared = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        android.os.Handler(Looper.myLooper()!!).postDelayed({
            if(dataUserShared.getString("id","").equals("")){
                gotoLogin()
            } else {
                //noteAddUpdateViewModel.deleteAllNotes()
                gotoHome()
            }
        },5000)

    }

    private fun gotoHome() {
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentSplash_to_fragmentHome)
    }

    private fun gotoLogin() {
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentSplash_to_fragmentLogin)

    }


}
package com.fal.crudfilmapps.fragment

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
import com.fal.crudfilmapps.databinding.FragmentRegisterBinding
import com.fal.crudfilmapps.viewModel.ViewModelUser


class FragmentRegister : Fragment() {

    lateinit var binding : FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ibSignUp.setOnClickListener{
            postUser()
        }
    }

    fun postUser(){
        var name = binding.etRegisterName.text.toString()
        var email = binding.etRegisterEmail.text.toString()
        var age = binding.etRegisterAge.text.toString()
        var address = binding.etRegisterAddress.text.toString()
        var password = binding.etRegisterPassword.text.toString()
        var passwordCorn = binding.etRegisterConfirmPassword.text.toString()
        
        if (name.length == 0 && email.length == 0 && age.length == 0 && address.length == 0 && password.length == 0 && passwordCorn.length == 0) {
            Toast.makeText(context, "Silahkan isi semua field yang ada", Toast.LENGTH_SHORT).show()
        } else {
            if(password.equals(passwordCorn)){
                val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
                viewModel.postApiUser(address,age.toInt(),0,name,name,password,email)
                viewModel.postLiveDataUser().observe(requireActivity(), Observer {
                    if (it != null){
                        gotoLogin()
                        Toast.makeText(context, "Add Data Successfull", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(context, "Password dan password konfirmasi tidak sama", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun gotoLogin(){
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentRegister_to_fragmentLogin)
    }

}
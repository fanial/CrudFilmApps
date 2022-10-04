package com.fal.crudfilmapps.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.databinding.FragmentLoginBinding
import com.fal.crudfilmapps.model.ResponseDataUserItem
import com.fal.crudfilmapps.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentLogin : Fragment() {

    lateinit var binding : FragmentLoginBinding
    private var user: ResponseDataUserItem? = null
    lateinit var dataUserShared : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataUserShared = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.ibSignIn.setOnClickListener{
            auth(binding.etLoginEmail.text.toString(),binding.etLoginPassword.text.toString())
        }
    }

    fun auth(username : String, password : String){
            RetrofitClient.instance.getAllUser()
                .enqueue(object : Callback<List<ResponseDataUserItem>> {
                    override fun onResponse(
                        call: Call<List<ResponseDataUserItem>>,
                        response: Response<List<ResponseDataUserItem>>
                    ) {
                        if (response.isSuccessful){
                            var responseBody = response.body()
                            if (responseBody != null) {
                                Log.d(TAG, "onResponse: ${responseBody.toString()}")
                                for (i in 0 until responseBody.size) {
                                    if(responseBody[i].username.equals(username) && responseBody[i].password.equals(password)) {
                                        var addData = dataUserShared.edit()
                                        addData.putString("address",responseBody[i].address)
                                        addData.putInt("age",responseBody[i].age)
                                        addData.putString("name",responseBody[i].name)
                                        addData.putString("password",responseBody[i].password)
                                        addData.putString("id",responseBody[i].id)
                                        addData.apply()
                                        gotoHome()
                                        Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                                        binding.tvAlertCantLogin.visibility = View.INVISIBLE
                                    } else {
                                        binding.tvAlertCantLogin.visibility = View.VISIBLE
//                                        Toast.makeText(context, "Username / Password salah", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }else{
                            Toast.makeText(context, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }

                })
    }
    fun gotoHome(){
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentLogin_to_fragmentHome2)
    }

}
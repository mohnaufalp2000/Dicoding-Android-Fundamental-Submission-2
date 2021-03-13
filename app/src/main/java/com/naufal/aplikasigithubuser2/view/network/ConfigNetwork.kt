package com.naufal.aplikasigithubuser2.view.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ConfigNetwork {

    companion object{

        fun getUser() : UserService{
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(UserService::class.java)
            return service
        }

    }

}
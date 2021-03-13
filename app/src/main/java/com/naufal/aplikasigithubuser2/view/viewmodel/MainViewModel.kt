package com.naufal.aplikasigithubuser2.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.model.User
import com.naufal.aplikasigithubuser2.view.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    val listUser = MutableLiveData<List<ItemsItem?>?>()


    fun getViewModelUser() : LiveData<List<ItemsItem?>?> {
        ConfigNetwork.getUser().getDataUser().enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(call: Call<List<ItemsItem>>, response: Response<List<ItemsItem>>) {
                if (response.isSuccessful){
                    val body = response.body()

                    listUser.postValue(body)
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
            }

        })
        return listUser
    }


}
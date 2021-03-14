package com.naufal.aplikasigithubuser2.view.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufal.aplikasigithubuser2.view.adapter.AdapterUser
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.model.User
import com.naufal.aplikasigithubuser2.view.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    val listSearchUser = MutableLiveData<ArrayList<ItemsItem?>?>()


    fun searchViewModelUser(username : String) {
        ConfigNetwork.getUser().searchDataUser(username).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val body = response.body()?.items
                    val listSearch : ArrayList<ItemsItem?> = arrayListOf()
                    if (body != null){
                        for (items in body){
                            listSearch.add(items)
                        }
                    }
                    listSearchUser.postValue(listSearch)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }

        })

    }

    fun getSearchUser() : LiveData<ArrayList<ItemsItem?>?>{
        return listSearchUser
    }



}
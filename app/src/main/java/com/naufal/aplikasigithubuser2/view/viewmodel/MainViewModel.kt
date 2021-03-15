package com.naufal.aplikasigithubuser2.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufal.aplikasigithubuser2.view.model.DetailUser
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.model.User
import com.naufal.aplikasigithubuser2.view.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    val listSearchUser = MutableLiveData<ArrayList<ItemsItem?>?>()
    val listDetailUser = MutableLiveData<DetailUser?>()


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

    fun detailViewModelUser(username : String?){
        ConfigNetwork.getUser().getDetailUser(username).enqueue(object : Callback<DetailUser>{
            override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                val body = response.body()
                val user = DetailUser(
                        name = body?.name,
                        login = body?.login,
                        avatarUrl = body?.avatarUrl,
                        followers = body?.followers,
                        following = body?.following,
                        publicRepos = body?.publicRepos,
                        location = body?.location,
                        company = body?.company,
                        reposUrl = body?.reposUrl
                )

                listDetailUser.postValue(user)
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {

            }


        })
    }

    fun getViewModelSearchUser() : LiveData<ArrayList<ItemsItem?>?>{
        return listSearchUser
    }

    fun getViewModelDetailUser() : LiveData<DetailUser?>{
        return listDetailUser
    }



}
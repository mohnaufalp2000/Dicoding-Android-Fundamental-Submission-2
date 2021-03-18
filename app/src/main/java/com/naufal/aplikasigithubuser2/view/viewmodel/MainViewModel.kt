package com.naufal.aplikasigithubuser2.view.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufal.aplikasigithubuser2.R
import com.naufal.aplikasigithubuser2.view.model.DetailUser
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.model.User
import com.naufal.aplikasigithubuser2.view.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    private val listSearchUser = MutableLiveData<ArrayList<ItemsItem?>?>()
    private val listDetailUser = MutableLiveData<DetailUser?>()


    fun searchViewModelUser(username : String, context: Context) {
        ConfigNetwork.getUser().searchDataUser(username).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                    val body = response.body()?.items

                    listSearchUser.postValue(body)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, R.string.connection_failed_desc, Toast.LENGTH_LONG).show()
            }

        })

    }

    fun getViewModelSearchUser() : LiveData<ArrayList<ItemsItem?>?>{
        return listSearchUser
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

    fun getViewModelDetailUser() : LiveData<DetailUser?>{
        return listDetailUser
    }



}
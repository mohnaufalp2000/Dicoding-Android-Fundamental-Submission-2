package com.naufal.aplikasigithubuser2.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufal.aplikasigithubuser2.view.model.Follower
import com.naufal.aplikasigithubuser2.view.model.Following
import com.naufal.aplikasigithubuser2.view.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentViewModel : ViewModel() {

    private val listFollowers = MutableLiveData<ArrayList<Follower>?>()
    private val listFollowings = MutableLiveData<ArrayList<Following>?>()

    fun followersViewModelUser(username : String?) {
        ConfigNetwork.getUser().getFollowerUser(username).enqueue(object : Callback<ArrayList<Follower>>{
            override fun onResponse(call: Call<ArrayList<Follower>>, response: Response<ArrayList<Follower>>) {
                val body = response.body()

                listFollowers.postValue(body)
            }

            override fun onFailure(call: Call<ArrayList<Follower>>, t: Throwable) {
            }
        })
    }

    fun getViewModelFollowers() : LiveData<ArrayList<Follower>?>{
        return listFollowers
    }

    fun followingsViewModelUser(username: String?){
        ConfigNetwork.getUser().getFollowingUser(username).enqueue(object : Callback<ArrayList<Following>>{
            override fun onResponse(call: Call<ArrayList<Following>>, response: Response<ArrayList<Following>>) {
                val body = response.body()

                listFollowings.postValue(body)
            }

            override fun onFailure(call: Call<ArrayList<Following>>, t: Throwable) {

            }

        })
    }

    fun getViewModelFollowings() : LiveData<ArrayList<Following>?>{
        return  listFollowings
    }

}
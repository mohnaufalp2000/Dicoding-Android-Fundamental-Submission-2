package com.naufal.aplikasigithubuser2.view.network

import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    @Headers("Authorization: token 373b6c1ce989e8531edbe986a3ee83cf3130ffed")
    fun searchDataUser( @Query("q") name : String) : Call<User>


}
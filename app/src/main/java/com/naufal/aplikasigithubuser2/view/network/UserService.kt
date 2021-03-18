package com.naufal.aplikasigithubuser2.view.network

import com.naufal.aplikasigithubuser2.view.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    @Headers("Authorization: token 373b6c1ce989e8531edbe986a3ee83cf3130ffed")
    fun searchDataUser( @Query("q") name : String) : Call<User>

    @GET("users/{username}")
    @Headers("Authorization: token 373b6c1ce989e8531edbe986a3ee83cf3130ffed")
    fun getDetailUser(@Path("username") username : String?) : Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 373b6c1ce989e8531edbe986a3ee83cf3130ffed")
    fun getFollowerUser(@Path("username") username : String? ) : Call<ArrayList<Follower>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 373b6c1ce989e8531edbe986a3ee83cf3130ffed")
    fun getFollowingUser(@Path("username") username : String?) : Call<ArrayList<Following>>

}
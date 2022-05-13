package com.pruebadeingreso.API

import com.pruebadeingreso.entities.Post
import com.pruebadeingreso.entities.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getUsers(@Url url:String) : Response<List<User>>

    @GET
    suspend fun getPostsOfUser(@Url url: String) : Response<List<Post>>
}
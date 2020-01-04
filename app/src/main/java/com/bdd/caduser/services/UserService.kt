package com.bdd.caduser.services

import com.bdd.caduser.models.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("user/")
    fun getAll(): Call<List<User>>

    @GET("user/{id}")
    fun getOne(@Path("id") id: Int): Call<User>

    @POST("user/")
    fun newUser(@Body user: User): Call<User>

    @PUT("user/{id}/")
    fun update(@Path("id") id: Int, @Body user: User): Call<User>

    @DELETE("user/{id}")
    fun delete(@Path("id") id: Int): Call<Unit>
}
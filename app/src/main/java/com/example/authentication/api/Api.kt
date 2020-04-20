package com.example.authentication.api

import com.example.authentication.models.DefaultResponse
import com.example.authentication.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("api/users/register")
    fun createUser(
        @Field("email") email:String,
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("username") username:String,
        @Field("confirmPassword") confirmPassword:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/users/login")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ):Call<LoginResponse>
}
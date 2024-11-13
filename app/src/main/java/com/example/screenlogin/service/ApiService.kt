package com.example.screenlogin.service

import com.example.screenlogin.repository.LoginRequest
import com.example.screenlogin.repository.LoginResponse
import com.example.screenlogin.repository.RegisterRequest
import com.example.screenlogin.repository.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}
package com.example.screenlogin.service

import com.example.screenlogin.repository.LoginRequest
import com.example.screenlogin.repository.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
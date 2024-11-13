package com.example.screenlogin.repository

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

@SerializedName("nickName")
val nickName: String,
@SerializedName("email")
val email: String,
@SerializedName("password")
val password: String
)
data class RegisterResponse(
    val message: String
)


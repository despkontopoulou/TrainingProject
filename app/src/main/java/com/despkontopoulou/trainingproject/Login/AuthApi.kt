package com.despkontopoulou.trainingproject.Login

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("Login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
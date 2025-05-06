package com.despkontopoulou.trainingproject.api

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("Login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
package com.despkontopoulou.trainingproject.Login

data class LoginRequest(
    val UserName: String,
    val Password: String
)

data class LoginResponse(
    val expires_in: Long,
    val token_type: String,
    val refresh_token:String,
    val access_token: String
)
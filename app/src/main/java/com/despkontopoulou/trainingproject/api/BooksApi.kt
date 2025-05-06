package com.despkontopoulou.trainingproject.api

import com.despkontopoulou.trainingproject.utils.Magazine
import retrofit2.http.GET
import retrofit2.http.Header

interface BooksApi {
    @GET("Books")
    suspend fun getBooks(
        @Header("Authorization") bearerToken: String
    ): List<Magazine>
}
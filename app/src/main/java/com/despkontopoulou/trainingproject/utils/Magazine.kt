package com.despkontopoulou.trainingproject.utils

import com.google.gson.annotations.SerializedName

data class Magazine(
    val id: Int,
    val title: String,

    @SerializedName("img_url")
    val imgUrl: String,

    @SerializedName("date_released")
    val dateReleased: String,

    @SerializedName("pdf_url")
    val pdfUrl: String
)
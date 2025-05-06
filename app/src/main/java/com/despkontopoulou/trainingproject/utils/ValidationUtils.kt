package com.despkontopoulou.trainingproject.utils

object ValidationUtils {
    val userIdRegex = Regex("^[A-Z]{2}[0-9]{4}\$")
    val passwordRegex = Regex(
        "^(?=(?:.*\\d){2,})(?=(?:.*[a-z]){3,})(?=(?:.*[A-Z]){2,})(?=(?:.*[^A-Za-z0-9]){1,}).{8,}$")
}
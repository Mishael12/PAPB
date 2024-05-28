package com.example.myapplication.data.model

import com.example.myapplication.model.User

data class UserModelResponse(
    val item: User? = User(),
    val key: String? = ""
)
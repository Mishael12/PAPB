package com.example.myapplication.data.model

import com.example.myapplication.model.Order

class OrderModelResponse (
    val item: Order? = Order(),
    val key: String? = ""
)
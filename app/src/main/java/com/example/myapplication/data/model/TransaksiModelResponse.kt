package com.example.myapplication.data.model

import com.example.myapplication.model.Transaksi

data class TransaksiModelResponse (
    val item: Transaksi? = Transaksi(),
    val key: String? = ""
)

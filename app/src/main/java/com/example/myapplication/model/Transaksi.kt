package com.example.myapplication.model

data class Transaksi(
    val transaksiId: String= "",
    val donasiId: String= "",
    val total : Long = 0,
    val day : String = "",
    val month: String = "",
    val year : String = "",
    val metodePembayaran: String= ""
)

package com.example.myapplication.model

import java.util.Date

data class Order(
    val idBarang : String = "",
    val day : String = "",
    val month: String = "",
    val year : String = "",
    val nama: String = "",
    val harga: Long = 0,
    val deskripsi: String ="",
    val jumlah: Long = 0,
    val total: Long = 0,
    val commission: Long = 0,
    val charge : Long = 0,
    val metodePembayaran : String = "",
    val userId: String = "",
)

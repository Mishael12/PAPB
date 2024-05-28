package com.example.myapplication.data.repo

import android.content.Context
import com.example.myapplication.data.Resource
import com.example.myapplication.model.Transaksi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class TransaksiRepo {
    private val db : DatabaseReference =
        FirebaseDatabase
            .getInstance("https://papbl-cb78d-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference
            .child("Transaksi")

    fun addTransaksi(transaksiData : Transaksi, context: Context) =
        callbackFlow<Resource<String>> {
            trySend(Resource.Loading())
            try{
                val transaksi =
                    Transaksi(
                        transaksiId = transaksiData.transaksiId,
                        donasiId = transaksiData.donasiId,
                        day = transaksiData.day,
                        month = transaksiData.month,
                        year = transaksiData.year,
                        total = transaksiData.total
                    )
                db
                    .child(transaksiData.transaksiId)
                    .setValue(transaksi)
                    .addOnSuccessListener {
                        trySend(Resource.Success("Berhasil menambahkan data"))
                    }
                    .addOnFailureListener{
                        trySend(Resource.Error(it.message.toString()))
                    }
            } catch (e: Exception) {
                trySend(Resource.Error(e.message.toString()))
            }
            awaitClose {
                close()
            }
        }

//    fun getTransaksi(user: String) =
//        callbackFlow<> {  }
}
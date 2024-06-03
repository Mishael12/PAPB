package com.example.myapplication.data.repo

import com.example.myapplication.data.Resource
import com.example.myapplication.model.Modal
import com.example.myapplication.model.Order
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.time.Month

class ModalRepo {
    private val db: DatabaseReference =
        FirebaseDatabase
            .getInstance("https://papbl-cb78d-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference
            .child("Modal")

    fun addModal(
        modalData : Modal
    ) = callbackFlow<Resource<String>> {
        trySend(Resource.Loading())
        try{
            val modal =
                Modal(
                    idModal = modalData.idModal,
                    modal = modalData.modal,
                    day = modalData.day,
                    month = modalData.month,
                    year = modalData.year,
                )

            db
                .child(modalData.idModal)
                .setValue(modal)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        trySend(Resource.Success(data = "Berhasil Menambahkan Modal"))
                    }
                }
                .addOnFailureListener {
                    trySend(Resource.Error(message = "Proses Penambahan Gagal\n${it.message}"))
                }
        }catch (e: Exception) {
            trySend(Resource.Error(e.message.toString()))
        }
        awaitClose {
            close()
        }

    }

    fun getModalByMonth(month: String) =
        callbackFlow<Resource<Long>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Modal::class.java)
                    }.filter {
                        it.month == month
                    }
                    val modal = items.sumOf { it.modal }
                    trySend(Resource.Success(modal))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

    fun getModalByDay(day: String) =
        callbackFlow<Resource<Long>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Modal::class.java)
                    }.filter {
                        it.month == day
                    }
                    val modal = items.sumOf { it.modal }
                    trySend(Resource.Success(modal))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

}
package com.example.myapplication.data.repo

import android.content.Context
import com.example.myapplication.data.Resource
import com.example.myapplication.data.model.OrderModelResponse
import com.example.myapplication.model.Order
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class OrderRepo {
    private val db: DatabaseReference =
        FirebaseDatabase
            .getInstance("https://papbl-cb78d-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference
            .child("Barang")
    var currentOrder: OrderModelResponse? = null

    fun addBarang(
        orderData: Order
    ) =
        callbackFlow<Resource<String>> {
            trySend(Resource.Loading())
            try {
                val order =
                    Order(
                        idBarang = orderData.idBarang,
                        nama = orderData.nama,
                        day = orderData.day,
                        month = orderData.month,
                        year = orderData.year,
                        harga = orderData.harga,
                        deskripsi = orderData.deskripsi,
                        jumlah = orderData.jumlah,
                        total = orderData.total,
                        commission = orderData.commission,
                        charge = orderData.charge,
                        metodePembayaran = orderData.metodePembayaran,
                        userId = orderData.userId
                    )
                db
                    .child(orderData.idBarang)
                    .setValue(order)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            trySend(Resource.Success(data = "Berhasil Menambahkan Makanan"))
                        }
                    }
                    .addOnFailureListener {
                        trySend(Resource.Error(message = "Proses Penambahan Gagal\n${it.message}"))
                    }
            } catch (e: Exception) {
                trySend(Resource.Error(e.message.toString()))
            }
            awaitClose {
                close()
            }
        }



    fun getTotalAndCountByMonth(month: String) =
        callbackFlow<Resource<Pair<Long, Long>>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Order::class.java)
                    }.filter {
                        it.month == month
                    }
                    val total = items.sumOf { it.total }
                    val count = items.sumOf { it.jumlah }
                    trySend(Resource.Success(Pair(total, count)))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

    fun getTotalAndCountByDay(day: String) =
        callbackFlow<Resource<Pair<Long, Long>>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Order::class.java)
                    }.filter {
                        it.day == day
                    }
                    val total = items.sumOf { it.total }
                    val count = items.sumOf { it.jumlah }
                    trySend(Resource.Success(Pair(total, count)))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

    fun getCommissionAndChargeByMonth(month: String) =
        callbackFlow<Resource<Pair<Long, Long>>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Order::class.java)
                    }.filter {
                        it.month == month
                    }
                    val commision = items.sumOf { it.commission }
                    val charge = items.sumOf { it.charge }
                    trySend(Resource.Success(Pair(commision, charge)))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

    fun getCommissionAndChargeByDay(day: String) =
        callbackFlow<Resource<Pair<Long, Long>>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Order::class.java)
                    }.filter {
                        it.day == day
                    }
                    val total = items.sumOf { it.commission }
                    val count = items.sumOf { it.charge }
                    trySend(Resource.Success(Pair(total, count)))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

    fun getTotalByPaymentMethodAndMonth(month: String) =
        callbackFlow<Resource<Map<String, Long>>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Order::class.java)
                    }.filter {
                        it.month == month
                    }

                    val paymentMethodTotals = items.groupBy { it.metodePembayaran }
                        .mapValues { entry ->
                            entry.value.sumOf { it.total }
                        }

                    trySend(Resource.Success(paymentMethodTotals))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

    fun getTotalByPaymentMethodAndDay(day: String) =
        callbackFlow<Resource<Map<String, Long>>> {
            trySend(Resource.Loading())
            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        it.getValue(Order::class.java)
                    }.filter {
                        it.day == day
                    }

                    val paymentMethodTotals = items.groupBy { it.metodePembayaran }
                        .mapValues { entry ->
                            entry.value.sumOf { it.total }
                        }

                    trySend(Resource.Success(paymentMethodTotals))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(error.message))
                }
            })
            awaitClose { close() }
        }

    suspend fun getItemsByDay(day: String): List<Order> {
        val snapshot = db.orderByChild("day").equalTo(day).get().await()
        return snapshot.children.mapNotNull { it.getValue(Order::class.java) }
    }

    fun deleteBarang(idBarang: String) =
        callbackFlow<Resource<String>> {
            trySend(Resource.Loading())
            db.child(idBarang).removeValue()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(Resource.Success(data = "Barang berhasil dihapus"))
                    } else {
                        trySend(Resource.Error(message = "Gagal menghapus barang"))
                    }
                }
                .addOnFailureListener {
                    trySend(Resource.Error(message = "Gagal menghapus barang\n${it.message}"))
                }
            awaitClose { close() }
        }


}
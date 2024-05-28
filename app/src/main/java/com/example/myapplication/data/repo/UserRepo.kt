package com.example.myapplication.data.repo

import com.example.myapplication.data.Resource
import com.example.myapplication.data.model.UserModelResponse
import com.example.myapplication.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserRepo() {
    private var auth = FirebaseAuth.getInstance()
    private var userDb = FirebaseDatabase
        .getInstance("https://papbl-cb78d-default-rtdb.asia-southeast1.firebasedatabase.app")
        .getReference("Users")
    var user: FirebaseUser? = null
    var currentUser: UserModelResponse? = null

    fun login(
        email : String,
        password : String
    ): Flow<Resource<String>> =
        callbackFlow{
            trySend(Resource.Loading())

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    user = it.user
                    if (user != null) {
                        trySend(Resource.Success("Berhasil Login"))
                    } else {
                        trySend(Resource.Error(message = "User tidak ditemukan"))
                    }
                }
                .addOnFailureListener{
                    trySend(Resource.Error(message = "Email atau password salah"))
                }
            awaitClose {
                close()
            }
    }
    fun registerUser(
        userData: User,
    ) =
        callbackFlow<Resource<String>> {
            trySend(Resource.Loading())

            auth.createUserWithEmailAndPassword(userData.email, userData.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = task.result.user?.uid

                        if (userId != null) {
                            userDb.child(userId).setValue(userData)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        trySend(Resource.Success(data = "Berhasil"))
                                    }
                                }
                        } else {
                            trySend(Resource.Error(message = "Gagal mendapatkan UID pengguna"))
                        }

                    } else {
                        trySend(Resource.Error(message = "Proses Registrasi Gagal"))
                    }
                }
                .addOnFailureListener {
                    trySend(Resource.Error(message = "Proses Registrasi Gagal\n${it.message}"))
                }
            awaitClose {
                close()
            }
        }

    fun getUserById(userId: String= user!!.uid): Flow<Resource<UserModelResponse>> =
        callbackFlow {
            trySend(Resource.Loading())

            userDb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.children.map {
                        UserModelResponse(it.getValue(User::class.java), it.key)
                    }.filter { it.key == userId }
                    currentUser = user[0]
                    trySend(Resource.Success(user[0]))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Resource.Error(message = error.message))
                }
            })
            awaitClose {
                close()
            }
        }
}
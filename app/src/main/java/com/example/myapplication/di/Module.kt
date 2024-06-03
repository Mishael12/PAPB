package com.example.myapplication.di

import com.example.myapplication.data.repo.ModalRepo
import com.example.myapplication.data.repo.OrderRepo
import com.example.myapplication.data.repo.TransaksiRepo
import com.example.myapplication.data.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideFirebaseAuth()= UserRepo()

    @Provides
    @Singleton
    fun provideOrderDatabase() = OrderRepo()

    @Provides
    @Singleton
    fun provideTransaksiDatabase() = TransaksiRepo()

    @Provides
    @Singleton
    fun provideModalDatabase() = ModalRepo()
}
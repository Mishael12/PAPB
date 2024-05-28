package com.example.myapplication.ui.presentation.detailTransaksi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Resource
import com.example.myapplication.data.repo.OrderRepo
import com.example.myapplication.data.repo.TransaksiRepo
import com.example.myapplication.data.repo.UserRepo
import com.example.myapplication.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailTransaksiViewModel @Inject constructor(
    private val orderRepo: OrderRepo,
    private val userRepo: UserRepo,
    private val transaksiRepo: TransaksiRepo

) : ViewModel(){
    private val _items: MutableState<List<Order>> = mutableStateOf(emptyList())
    val items: State<List<Order>> = _items

    fun fetchItemsByDay(day: String) {
        viewModelScope.launch {
            val result = orderRepo.getItemsByDay(day)
            _items.value = result
        }
    }
}
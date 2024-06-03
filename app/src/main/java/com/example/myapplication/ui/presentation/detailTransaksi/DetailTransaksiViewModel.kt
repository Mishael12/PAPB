package com.example.myapplication.ui.presentation.detailTransaksi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Resource
import com.example.myapplication.data.repo.ModalRepo
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
    private val modalRepo: ModalRepo,

    ) : ViewModel(){
    private val _items: MutableState<List<Order>> = mutableStateOf(emptyList())
    val items: State<List<Order>> = _items

    fun fetchItemsByDay(day: String) {
        viewModelScope.launch {
            val result = orderRepo.getItemsByDay(day)
            _items.value = result
        }
    }
    private val _modal : MutableState<Long?> = mutableStateOf(null)
    private val _chargeBulan : MutableState<Long?> = mutableStateOf(null)
    private val _commissionDay: MutableState<Long?> = mutableStateOf(null)
    private val _commissionMonth: MutableState<Long?> = mutableStateOf(null)
    private val _chargeDay : MutableState<Long?> = mutableStateOf(null)
    private val _countBulan: MutableState<Long?> = mutableStateOf(null)
    private val _totalBulan : MutableState<Long?> = mutableStateOf(null)
    private val _countDay: MutableState<Long?> = mutableStateOf(null)
    private val _totalDay : MutableState<Long?> = mutableStateOf(null)
    private val _month : MutableState<String> = mutableStateOf("")
    private val _day : MutableState<String> = mutableStateOf("")
    private val _year : MutableState<String> = mutableStateOf("")

    private val _paymentMethodTotals: MutableState<Map<String, Long>?> = mutableStateOf(null)

    val paymentMethodTotals: State<Map<String, Long>?> = _paymentMethodTotals
    val chargeBulan: State<Long?> = _chargeBulan

    val modal: State<Long?> = _modal
    val commissionMonth: State<Long?> = _commissionMonth
    val commissionDay: State<Long?> = _commissionDay
    val chargeDay: State<Long?> = _chargeDay
    val countMonth: State<Long?> = _countBulan
    val totalMonth: State<Long?> = _totalBulan
    val countDay: State<Long?> = _countDay
    val totalDay: State<Long?> = _totalDay
    val month : State<String> = _month
    val day : State<String> = _day
    val year : State<String> = _year



    fun fetchTotalModal(){
        if (_month.value.isNotEmpty()) {
            viewModelScope.launch {
                modalRepo.getModalByDay(_day.value).collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Handle loading state if needed
                        }
                        is Resource.Success -> {
                            _modal.value = resource.data
                        }
                        is Resource.Error -> {
                            // Handle error state if needed
                        }
                    }
                }
            }
        } else {
            _modal.value = null
        }
    }

    fun onChangeMonth(value: String){
        _month.value = value
    }

    fun onChangeYear(value : String){
        _year.value = value
    }

    fun onChangeDay(value: String){
        _day.value = value
        fetchTotalModal()
    }

    fun deleteItem(idBarang: String) {
        viewModelScope.launch {
            orderRepo.deleteBarang(idBarang).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        // Handle loading state if necessary
                    }
                    is Resource.Success -> {
                        // Fetch updated list after deletion
                        fetchItemsByDay(_day.value)
                    }
                    is Resource.Error -> {
                        // Handle error state if necessary
                    }
                }
            }
        }
    }
}
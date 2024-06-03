package com.example.myapplication.ui.presentation.manage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Resource
import com.example.myapplication.data.repo.OrderRepo
import com.example.myapplication.data.repo.TransaksiRepo
import com.example.myapplication.data.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageViewModel@Inject constructor(
    private val orderRepo: OrderRepo,
    private val userRepo: UserRepo,
    private val transaksiRepo: TransaksiRepo

) : ViewModel(){
    private val _commissionBulan: MutableState<Long?> = mutableStateOf(null)
    private val _chargeBulan : MutableState<Long?> = mutableStateOf(null)
    private val _commissionDay: MutableState<Long?> = mutableStateOf(null)
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

    val commissionDay: State<Long?> = _commissionDay
    val chargeDay: State<Long?> = _chargeDay
    val countMonth: State<Long?> = _countBulan
    val totalMonth: State<Long?> = _totalBulan
    val countDay: State<Long?> = _countDay
    val totalDay: State<Long?> = _totalDay
    val month : State<String> = _month
    val day : State<String> = _day
    val year : State<String> = _year



    init {
        fetchCommissionAndChargeDay()
        fetchCommissionAndChargeMonth()
        fetchTotalAndCountDay()
        fetchTotalAndCountMonth()
        fetchTotalsByPaymentMethod()

    }



    fun fetchTotalsByPaymentMethod() {
        val month = _month.value
        if (month.isNotEmpty()) {
            viewModelScope.launch {
                orderRepo.getTotalByPaymentMethodAndMonth(month).collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _paymentMethodTotals.value = resource.data
                        }
                        is Resource.Error -> {
                            // Handle error
                        }
                        is Resource.Loading -> {
                            // Handle loading
                        }
                    }
                }
            }
        }
    }

    private fun fetchCommissionAndChargeDay(){
        if (_day.value.isNotEmpty()) {
            viewModelScope.launch {
                orderRepo.getCommissionAndChargeByDay(_day.value).collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Handle loading state if needed
                        }
                        is Resource.Success -> {
                            _commissionDay.value = resource.data?.first
                            _chargeDay.value = resource.data?.second
                        }
                        is Resource.Error -> {
                            // Handle error state if needed
                        }
                    }
                }
            }
        } else {
            _commissionDay.value = null
            _chargeDay.value = null
        }
    }

    private fun fetchCommissionAndChargeMonth() {
        if (_month.value.isNotEmpty()) {
            viewModelScope.launch {
                orderRepo.getCommissionAndChargeByMonth(_month.value).collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Handle loading state if needed
                        }
                        is Resource.Success -> {
                            _commissionBulan.value = resource.data?.first
                            _chargeBulan.value = resource.data?.second
                        }
                        is Resource.Error -> {
                            // Handle error state if needed
                        }
                    }
                }
            }
        } else {
            _commissionBulan.value = null
            _chargeBulan.value = null
        }
    }

    private fun fetchTotalAndCountDay(){
        if (_day.value.isNotEmpty()) {
            viewModelScope.launch {
                orderRepo.getTotalAndCountByDay(_day.value).collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Handle loading state if needed
                        }
                        is Resource.Success -> {
                            _totalDay.value = resource.data?.first
                            _countDay.value = resource.data?.second
                        }
                        is Resource.Error -> {
                            // Handle error state if needed
                        }
                    }
                }
            }
        } else {
            _totalDay.value = null
            _countDay.value = null
        }
    }

    private fun fetchTotalAndCountMonth() {
        if (_month.value.isNotEmpty()) {
            viewModelScope.launch {
                orderRepo.getTotalAndCountByMonth(_month.value).collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Handle loading state if needed
                        }
                        is Resource.Success -> {
                            _totalBulan.value = resource.data?.first
                            _countBulan.value = resource.data?.second
                        }
                        is Resource.Error -> {
                            // Handle error state if needed
                        }
                    }
                }
            }
        } else {
            _totalBulan.value = null
            _countBulan.value = null
        }
    }

    fun onChangeMonth(value: String){
        _month.value = value
        fetchTotalAndCountMonth()
        fetchCommissionAndChargeMonth()
    }

    fun onChangeYear(value : String){
        _year.value = value
    }

    fun onChangeDay(value: String){
        _day.value = value
        fetchTotalAndCountDay()
        fetchCommissionAndChargeDay()
    }

    fun calculateNetTotalDay(): Long {
        val totalDay = _totalDay.value ?: 0
        val commissionDay = _commissionDay.value ?: 0
        val chargeDay = _chargeDay.value ?: 0
        return totalDay - commissionDay - chargeDay
    }
}
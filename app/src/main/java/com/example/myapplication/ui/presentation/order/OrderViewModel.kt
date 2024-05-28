package com.example.myapplication.ui.presentation.order

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Resource
import com.example.myapplication.data.model.UserModelResponse
import com.example.myapplication.data.repo.OrderRepo
import com.example.myapplication.data.repo.UserRepo
import com.example.myapplication.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepo: OrderRepo,
    private val userRepo: UserRepo

) : ViewModel() {

    private val _userData = mutableStateOf(UserModelResponse())
    private val _namaBarang : MutableState<String> = mutableStateOf("")
    private val _deskripsi : MutableState<String> = mutableStateOf("")
    private val _harga : MutableState<Long?> = mutableStateOf(null)
    private val _isLoading : MutableState<Boolean> = mutableStateOf(false)
    private val _metode : MutableState<String> = mutableStateOf("")
    private val _month : MutableState<String> = mutableStateOf("")
    private val _day : MutableState<String> = mutableStateOf("")
    private val _year : MutableState<String> = mutableStateOf("")
    private val _jumlah : MutableState<Long?> = mutableStateOf(null)
    private val _total : MutableState<Long?> = mutableStateOf(null)
    private val _commission : MutableState<Long?> = mutableStateOf(null)
    private val _charge : MutableState<Long?> = mutableStateOf(null)

    val userData : State<UserModelResponse> = _userData
    val namaBarang : State<String> = _namaBarang
    val deskripsi : State<String> = _deskripsi
    val metode : State<String> = _metode
    val harga : State<Long?> = _harga
    val month : State<String> = _month
    val day : State<String> = _day
    val year : State<String> = _year
    val jumlah : State<Long?> = _jumlah
    val total : State<Long?> = _total
    val isLoading : State<Boolean> = _isLoading
    val commission : State<Long?> = _commission
    val charge : State<Long?> = _charge

    private val _isShowDialog: MutableState<Boolean> = mutableStateOf(false)
    val isShowDialog: State<Boolean> = _isShowDialog

    fun onChangeNamaBarang(value : String){
        _namaBarang.value = value
    }

    fun onChangeDeskripsi(value : String){
        _deskripsi.value = value
    }

    fun onChangeMetode(value : String){
        _metode.value = value
    }

    fun onChangeMonth(value: String){
        _month.value = value
    }

    fun onChangeYear(value : String){
        _year.value = value
    }

    fun onChangeDay(value: String){
        _day.value = value
    }

    fun onChangeHarga(value: Long?){
        _harga.value = value
        calculateTotal()
    }

    fun onChangeJumlah(value: Long?){
        _jumlah.value = value
        calculateTotal()
    }

    fun onChangeCommission(value: Long?){
        _commission.value = value
    }

    fun onChangeCharge(value: Long?){
        _charge.value = value
    }

    fun setDialog(state: Boolean) {
        _isShowDialog.value = state
    }


    fun setLoading(state: Boolean){
        _isLoading.value = state
    }

    private fun calculateTotal() {
        val harga = _harga.value ?: 0L
        val jumlah = _jumlah.value ?: 0L
        _total.value = if (jumlah == 0L) {
            harga
        } else {
            harga * jumlah
        }
    }

    fun checkValid(context: Context) : Boolean {
        var isValid : Boolean = true
        if (_namaBarang.value.isEmpty() || _deskripsi.value.isEmpty() || _harga.value?.toString().isNullOrEmpty()){
            Toast.makeText(context, "Semua data harus diisi", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }



    fun getUser() {
        viewModelScope.launch {
            userRepo.getUserById().collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _userData.value = it.data!!
                        _isLoading.value = false
                    }
                    is Resource.Error -> {
                        UserModelResponse(item = null, key = null)
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun addBarang(context: Context) : Flow<Resource<String>> {
        val id = UUID.randomUUID().toString()
         val barang = Order(
             idBarang = id,
             month = _month.value,
             day = _day.value,
             year = _year.value,
             nama= _namaBarang.value,
             harga= _harga.value!!,
             jumlah = _jumlah.value!!,
             total = _total.value!!,
             deskripsi = _deskripsi.value,
             metodePembayaran = _metode.value,
             commission = _commission.value!!,
             charge = _charge.value!!,
             userId= _userData.value.key!!
         )
        return orderRepo.addBarang(
            orderData = barang
        )
    }

    fun resetFields() {
        _namaBarang.value = ""
        _deskripsi.value = ""
        _harga.value = null
        _jumlah.value = null
        _total.value = null
        _day.value = ""
        _month.value = ""
        _year.value = ""
        _commission.value = null
        _charge.value = null
        _metode.value = ""
    }


}
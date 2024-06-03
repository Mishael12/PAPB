package com.example.myapplication.ui.presentation.modal

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Resource
import com.example.myapplication.data.repo.ModalRepo
import com.example.myapplication.model.Modal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ModalViewModel @Inject constructor(
    private val modalRepo : ModalRepo
) : ViewModel(){
    private val _modal : MutableState<Long?> = mutableStateOf(null)
    private val _month : MutableState<String> = mutableStateOf("")
    private val _day : MutableState<String> = mutableStateOf("")
    private val _year : MutableState<String> = mutableStateOf("")
    private val _isLoading : MutableState<Boolean> = mutableStateOf(false)

    val month : State<String> = _month
    val day : State<String> = _day
    val year : State<String> = _year
    val modal : State<Long?> = _modal

    private val _isShowDialog: MutableState<Boolean> = mutableStateOf(false)
    val isShowDialog: State<Boolean> = _isShowDialog

    fun onChangeMonth(value: String){
        _month.value = value
    }

    fun onChangeYear(value : String){
        _year.value = value
    }

    fun onChangeDay(value: String){
        _day.value = value
    }

    fun onChangeModal(value: Long?){
        _modal.value = value
    }

    fun setDialog(state: Boolean) {
        _isShowDialog.value = state
    }


    fun setLoading(state: Boolean){
        _isLoading.value = state
    }
    fun checkValid(context: Context) : Boolean {
        var isValid : Boolean = true
        if (_modal.value.toString().isEmpty()){
            Toast.makeText(context, "Modal harus diisi", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }
    fun addModal(context: Context) : Flow<Resource<String>> {
        val id = UUID.randomUUID().toString()
        val modal = Modal(
            idModal = id,
            month = _month.value,
            day = _day.value,
            year = _year.value,
            modal = _modal.value!!
        )
        return modalRepo.addModal(
            modalData = modal
        )
    }
    fun resetFields() {
        _day.value = ""
        _month.value = ""
        _year.value = ""
        _modal.value = null
    }
}
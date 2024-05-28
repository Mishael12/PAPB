package com.example.myapplication.ui.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: UserRepo
) : ViewModel(){
    private val _email: MutableState<String> = mutableStateOf("")
    private val _password: MutableState<String> = mutableStateOf("")
    private val _check: MutableState<Boolean> = mutableStateOf(false)
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)

    val email: State<String> = _email
    val password: State<String> = _password
    val check: State<Boolean> = _check
    val isLoading: State<Boolean> = _isLoading

    fun isNotValid() =_email.value.isEmpty() || _password.value.isEmpty()

    fun submit() = userRepo.login(_email.value, _password.value)
    fun onChangeEmail(value: String){
        _email.value = value
    }

    fun onChangePassword(value: String){
        _password.value = value
    }
    fun checked(){
        _check.value = !_check.value
    }
    fun setLoading(state: Boolean) {
        _isLoading.value = state
    }
}
package com.example.myapplication.ui.presentation.register

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Resource
import com.example.myapplication.data.repo.UserRepo
import com.example.myapplication.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepo : UserRepo

) : ViewModel() {
    private val _username: MutableState<String> = mutableStateOf("")
    private val _email: MutableState<String> = mutableStateOf("")
    private val _password: MutableState<String> = mutableStateOf("")
    private val _confPassword: MutableState<String> = mutableStateOf("")
    private val _isShowDialog: MutableState<Boolean> = mutableStateOf(false)
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)


    val username: State<String> = _username
    val email: State<String> = _email
    val password: State<String> = _password
    val confPass: State<String> = _confPassword
    val isShowDialog: State<Boolean> = _isShowDialog
    val isLoading: State<Boolean> = _isLoading

    fun onChangeUsername(value: String){
        _username.value = value
    }

    fun onChangeEmail(value: String){
        _email.value = value
    }

    fun onChangePassword(value: String){
        _password.value = value
    }

    fun onChangeConfPass(value: String){
        _confPassword.value = value
    }

    fun setDialog(show: Boolean) {
        _isShowDialog.value = show
    }

    fun setLoading(state: Boolean) {
        _isLoading.value = state
    }

    fun check(context: Context) : Boolean{
        var valid: Boolean?
        if (_username.value.isEmpty() || _email.value.isEmpty() || _password.value.isEmpty() || _confPassword.value.isEmpty()){
            Toast.makeText(context, "Data harus terisi", Toast.LENGTH_SHORT).show()
            valid = false
            return valid
        }else if(!_email.value.contains("@")){
            Toast.makeText(context, "Perhatikan Syarat", Toast.LENGTH_SHORT).show()
            valid = false
            return valid
        }else{
            if (_password.value != _confPassword.value){
                Toast.makeText(context, "Perhatikan Password", Toast.LENGTH_SHORT).show()
                valid = false
                return valid
            }
        }
        valid = true
        return valid
    }

    fun onSubmit(): Flow<Resource<String>> {
        val user = User(
            username = _username.value,
            email = _email.value,
            password = _password.value

        )
        return userRepo.registerUser(
            userData = user,
        )
    }

}
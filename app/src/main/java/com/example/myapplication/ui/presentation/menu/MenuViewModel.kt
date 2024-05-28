package com.example.myapplication.ui.presentation.menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Resource
import com.example.myapplication.data.model.UserModelResponse
import com.example.myapplication.data.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val userRepo: UserRepo
) : ViewModel(){
    private val _userData = mutableStateOf(UserModelResponse())
    private val _isLoading = mutableStateOf(false)

    val userData: State<UserModelResponse> = _userData
    val isLoading: State<Boolean> = _isLoading

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
}
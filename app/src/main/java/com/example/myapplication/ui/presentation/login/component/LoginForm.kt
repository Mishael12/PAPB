package com.example.myapplication.ui.presentation.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ButtonType
import com.example.myapplication.Route
import com.example.myapplication.data.Resource
import com.example.myapplication.ui.presentation.component.CustomButton
import com.example.myapplication.ui.presentation.component.CustomTextField
import com.example.myapplication.ui.presentation.login.LoginViewModel
import com.example.myapplication.ui.theme.Neutral900
import com.example.myapplication.ui.theme.Primary800
import com.example.myapplication.ui.theme.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginForm(
    viewModel: LoginViewModel, navController: NavController, scope: CoroutineScope
){
    val context = LocalContext.current

    Text(
        text = "Email",
        style = Type.textSmMedium()
    )
    CustomTextField(
        text = viewModel.email.value,
        placeholder = "",
        onValueChange = {
            viewModel.onChangeEmail(it)
        }
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = "Password",
        style = Type.textSmMedium()
    )
    CustomTextField(
        text = viewModel.password.value,
        placeholder = "",
        trailingIcon = Icons.Filled.Visibility,
        isPassword = true,
        showPassword = true,
        onValueChange = {
            viewModel.onChangePassword(it)
        }

    )
    Spacer(modifier = Modifier.height(15.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

        }
        Text(
            text = "Lupa Sandi?",
            style = Type.text2xsRegular(),
            color = Neutral900,
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CustomButton(
            text = "Login",
            type = ButtonType.LARGE,
            onClick = {
                if(!viewModel.isNotValid()){
                    scope.launch {
                        viewModel.submit().collect{
                            when (it){
                                is Resource.Error -> {
                                    viewModel.setLoading(false)
                                }
                                is Resource.Loading -> {
                                    viewModel.setLoading(true)
                                }
                                is Resource.Success -> {
                                    navController.popBackStack()
                                    navController.navigate(Route.MAIN)
                                }
                            }
                        }
                    }
                }
            }
        )
    }


}
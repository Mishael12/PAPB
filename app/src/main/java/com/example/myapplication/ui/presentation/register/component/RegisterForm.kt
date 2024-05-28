package com.example.myapplication.ui.presentation.register.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.ButtonType
import com.example.myapplication.Route
import com.example.myapplication.data.Resource
import com.example.myapplication.ui.presentation.component.CustomButton
import com.example.myapplication.ui.presentation.component.CustomTextField
import com.example.myapplication.ui.presentation.register.RegisterViewModel
import com.example.myapplication.ui.theme.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterForm(
    viewModel: RegisterViewModel, navController: NavController, scope: CoroutineScope, context: Context
){
    LazyColumn (

    ){
        item{
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Username", style = Type.textSmMedium())
            CustomTextField(
                text = viewModel.username.value,
                placeholder = "Username",
                onValueChange = {
                    viewModel.onChangeUsername(it)
                })
        }
        item {
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Email", style = Type.textSmMedium() )
            CustomTextField(
                text = viewModel.email.value,
                placeholder = "Email",
                onValueChange = {
                    viewModel.onChangeEmail(it)
                })
        }
        item {
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Password", style = Type.textSmMedium() )
            CustomTextField(
                text = viewModel.password.value,
                placeholder = "Password",
                isPassword = true,
                trailingIcon = Icons.Default.Clear,
                onValueChange = {
                    viewModel.onChangePassword(it)
                })
        }
        item {
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Confirm Password", style = Type.textSmMedium() )
            CustomTextField(
                text = viewModel.confPass.value,
                placeholder = "Confirm Password",
                isPassword = true,
                trailingIcon = Icons.Default.Clear,
                onValueChange = {
                    viewModel.onChangeConfPass(it)
                })
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CustomButton(
                    text = "Register",
                    type = ButtonType.LARGE,
                    onClick = {
                        if (viewModel.check(context)){
                            scope.launch {
                                viewModel.onSubmit().collect{
                                    when(it){
                                        is Resource.Success -> {
                                            viewModel.setDialog(true)
                                            navController.navigate(Route.INTRO)
                                        }
                                        is Resource.Error -> {
                                            viewModel.setLoading(false)
                                            Toast.makeText(context, it.message, Toast
                                                .LENGTH_SHORT).show()
                                        }
                                        is Resource.Loading -> {
                                            viewModel.setLoading(true)
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }

    }
}
package com.example.myapplication.ui.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.presentation.register.component.Head
import com.example.myapplication.ui.presentation.register.component.RegisterForm
import com.example.myapplication.ui.theme.Neutral900
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxSize()
            .background(primary)
    ){
        Head(navController = navController)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            AsyncImage(
                model = R.drawable.logogg,
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(start = 25.dp, end = 20.dp)
            ){

                Column {

                    Text(
                        text = "Register",
                        color = Neutral900,
                        style = Type.displayXsSemiBold(),
                    )

                    Text(
                        text = "Please register to login.",
                        color = Neutral900,
                        style = Type.text2xsSemiBold()
                    )
                    RegisterForm(viewModel = viewModel, navController = navController, context= context,
                        scope = scope
                    )
                }
            }
        }

    }
}
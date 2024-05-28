package com.example.myapplication.ui.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.Route
import com.example.myapplication.ui.presentation.login.component.LoginForm
import com.example.myapplication.ui.theme.Neutral50
import com.example.myapplication.ui.theme.Neutral900
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val scope = rememberCoroutineScope()
    Box(
        Modifier
            .fillMaxSize()
            .background(primary),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            AsyncImage(
                model = R.drawable.logogg,
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp))
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
            ) {

                Column {
                    Text(
                        text = "Login",
                        color = Neutral900,
                        style = Type.displayXsSemiBold(),
                    )
                    Text(
                        text = "Please sign in to continue.",
                        color = Neutral900,
                        style = Type.text2xsSemiBold()
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    LoginForm(viewModel, navController, scope)

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Don't have an account?",
                            style = Type.text2xsMedium(),
                            color = Neutral900
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Sign in",
                            style = Type.textXlBold(),
                            modifier = Modifier.clickable {
                                navController.navigate(Route.REGISTER)
                            }

                        )
                    }
                }
            }
        }
    }
}




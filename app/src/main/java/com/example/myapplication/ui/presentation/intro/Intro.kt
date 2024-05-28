package com.example.myapplication.ui.presentation.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.ButtonType
import com.example.myapplication.R
import com.example.myapplication.Route
import com.example.myapplication.ui.presentation.component.CustomButton
import com.example.myapplication.ui.presentation.intro.component.Header
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary

@Composable
fun Intro(
    navController: NavController
){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Congratulations! ",
                color = Color.Black,
                fontSize = 33.sp,
                style = Type.displayXsSemiBold2(),
                modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Header()
            Spacer(modifier = Modifier.height(100.dp))

            AsyncImage(
                model = R.drawable.first,
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(70.dp))
            CustomButton(
                text = "Continue",
                type = ButtonType.LARGE,
                onClick = {
                    navController.navigate(Route.LOGIN)
                }
            )

        }
    }
}
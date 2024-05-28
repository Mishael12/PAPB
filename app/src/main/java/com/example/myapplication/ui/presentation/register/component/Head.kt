package com.example.myapplication.ui.presentation.register.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Route

@Composable
fun Head(navController: NavController){
    Row(
        Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 48.dp)
    ){
        IconButton(onClick = {
            navController.popBackStack()
            navController.navigate(Route.LOGIN)
        })
        {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.Black)
        }
    }
}
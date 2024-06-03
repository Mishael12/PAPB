package com.example.myapplication.ui.presentation.statistic.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Route
import com.example.myapplication.ui.theme.Type

@Composable
fun StatisticHeader(
    navController: NavController
){
    Row(
        Modifier
            .fillMaxSize()
            .padding(start = 19.dp, top = 48.dp)
    ){
        IconButton(onClick = {
            navController.popBackStack()
            navController.navigate(Route.MAIN)
        })
        {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "",
                tint = Color.Black)
        }
        Spacer(modifier = Modifier.width(55.dp))
        Text(
            text = "Statistic Perbulan",
            style = Type.text2xsSemiBold(),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 9.dp))
    }
}
package com.example.myapplication.ui.presentation.menu.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.User
import com.example.myapplication.ui.theme.Type

@Composable
fun HeaderMenu(userData: User){
    Text(
        modifier = Modifier
            .padding(start = 20.dp),
        text = "Welcome, ${userData.username}",
        color = Color.White,
        fontSize = 25.sp,
        style = Type.text2xsMedium()

    )
}
package com.example.myapplication.ui.presentation.intro.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Type

@Composable
fun Header(){
    Text(
        text = "You've successfully created your account.",
        color = Color.Black,
        fontSize = 12.5.sp,
        textAlign = TextAlign.Left,
        style = Type.displayXsSemiBold2(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 65.dp)

    )
    Text(
        text = "Welcome to CozyNest! Explore the ",
        color = Color.Black,
        fontSize = 12.5.sp,
        textAlign = TextAlign.Left,
        style = Type.displayXsSemiBold2(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 65.dp)
    )
    Text(
        text = "features. If you have any questions, our ",
        color = Color.Black,
        fontSize = 12.5.sp,
        textAlign = TextAlign.Justify,
        style = Type.displayXsSemiBold2(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 65.dp)

    )
    Text(
        text = "support team is here to help. Happy ",
        color = Color.Black,
        fontSize = 12.5.sp,
        textAlign = TextAlign.Justify,
        style = Type.displayXsSemiBold2(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 65.dp)

    )
    Text(
        text = "exploring!",
        color = Color.Black,
        fontSize = 12.5.sp,
        textAlign = TextAlign.Justify,
        style = Type.displayXsSemiBold2(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 65.dp)

    )
}
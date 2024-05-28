package com.example.myapplication.ui.presentation.statistic.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLineCard(){
    Column(
        Modifier.width(IntrinsicSize.Max)) {
        Divider(color = Color.Black, modifier = Modifier.width(310.dp).height(2.dp))
    }
}
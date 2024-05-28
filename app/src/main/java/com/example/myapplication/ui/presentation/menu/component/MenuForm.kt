package com.example.myapplication.ui.presentation.menu.component

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Task
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ButtonType
import com.example.myapplication.Route
import com.example.myapplication.data.Resource
import com.example.myapplication.ui.presentation.component.CustomButton
import com.example.myapplication.ui.presentation.menu.MenuViewModel

@Composable
fun MenuForm(
    navController: NavController
){
    Spacer(modifier = Modifier.height(30.dp))
    CustomButton(
        onClick = {
            navController.navigate(Route.ORDER)
        },
        text = "ADD ORDER",
        type = ButtonType.SMALL,
        imageVector = Icons.Filled.AddBox, // Pass the icon here
        modifier = Modifier
    )
    Spacer(modifier = Modifier.height(35.dp))
    CustomButton(
        onClick = {
                  navController.navigate(Route.TRANSAKSI)
        },
        text = "STATISTIC   ",
        type = ButtonType.SMALL,
        imageVector = Icons.Filled.AutoGraph, // Pass the icon here
        modifier = Modifier
    )
    Spacer(modifier = Modifier.height(35.dp))
    CustomButton(
        onClick = {
            navController.navigate(Route.LOGIN)
        },
        text = "Log out          ",
        type = ButtonType.SMALL,
        imageVector = Icons.Filled.Logout, // Pass the icon here
        modifier = Modifier
    )
}
package com.example.myapplication.ui.presentation.order.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ButtonType
import com.example.myapplication.ui.presentation.component.CustomButton

@SuppressLint("RestrictedApi")
@Composable
fun Content(
    index: Int
){
    when(index){
        0 -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Travel (10x10cm)",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Medium (22x10cm)",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Large (22x22cm)",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Brownis Cup",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Single Box",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Regular Box",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Big Deals Box",
                        type = ButtonType.ORDER
                    )
                }
            }
        }
        1 -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Original 10 pcs",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Mix Topping 10 pcs",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Pie Brownis 5 pcs",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Pie Buah",
                        type = ButtonType.ORDER
                    )
                }
            }
        }
        2 -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Original 20 cm",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Pie Buah Jumbo",
                        type = ButtonType.ORDER
                    )

                }
            }
        }
        3 -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Risoles Mayo",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Risoles Rogout",
                        type = ButtonType.ORDER
                    )

                }
            }
        }
        4 -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Nastar",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Kastengel Double Cheese",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Snow White",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Palm Sugar",
                        type = ButtonType.ORDER
                    )
                }
            }
        }
        5 -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Coklat",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Strawberry",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Greentea",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Taro",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Tiramisu",
                        type = ButtonType.ORDER
                    )
                }
            }
        }
        6 -> {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Silk Puding",
                        type = ButtonType.ORDER
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomButton(
                        onClick = { /*TODO*/ },
                        text = "Coconut Puding Delight",
                        type = ButtonType.ORDER
                    )
                }
            }
        }
    }
}
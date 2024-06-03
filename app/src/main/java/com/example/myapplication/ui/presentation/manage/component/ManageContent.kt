package com.example.myapplication.ui.presentation.manage.component

import ShowDatePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Route
import com.example.myapplication.ui.presentation.manage.ManageViewModel
import com.example.myapplication.ui.presentation.statistic.StatisticViewModel
import com.example.myapplication.ui.presentation.statistic.component.HorizontalLineCard
import com.example.myapplication.ui.theme.Neutral51
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary
import com.example.myapplication.ui.theme.primary2
import kotlinx.coroutines.CoroutineScope

@Composable
fun ManageContent(
    navController: NavController,
    viewModel: ManageViewModel,
    scope: CoroutineScope
){
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    val paymentMethodTotals by viewModel.paymentMethodTotals

    if (showDatePicker) {
        ShowDatePickerDialog(
            context = context,
            onDateSelected = { day, month, year ->
                viewModel.onChangeDay(day.toString())
                viewModel.onChangeMonth(month.toString())
                viewModel.onChangeYear(year.toString())
                viewModel.fetchTotalsByPaymentMethod()
                showDatePicker = false
            }
        )
    }

    val month by viewModel.month
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
            .padding(20.dp)
    ){
        item {
            Row {
                Button(onClick = { showDatePicker = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Neutral51,
                        contentColor = Color.White)) {
                    Text(text = "Pilih Tanggal")
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Card (
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(primary2),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, top = 7.dp)
                ){
                    Text(
                        text = "Tanggal  :",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "${viewModel.day.value} - ${viewModel.month.value} - ${viewModel.year.value}",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        item{
            Card (
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(primary2),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, top = 7.dp)
                ){
                    Text(
                        text = "Pendapatan Perhari",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Text(
                            text = "Item terjual",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box (
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 37.dp),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            val totalText = if (viewModel.countDay.value != null) viewModel.countDay.value.toString() else "0"
                            Text(
                                text = totalText,
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Text(
                            text = "Total Penjualan",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 37.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            val totalText = if (viewModel.totalDay.value != null) viewModel.totalDay.value.toString() else "0"
                            Text(
                                text = "Rp. $totalText",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    HorizontalLineCard()
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "Commission",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 37.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            val totalText = if (viewModel.commissionDay.value != null) viewModel.commissionDay.value.toString() else "0"
                            Text(
                                text = "Rp. $totalText",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "Modal",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 37.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = "Rp. 0",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "Charge",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 37.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            val totalText = if (viewModel.chargeDay.value != null) viewModel.chargeDay.value.toString() else "0"
                            Text(
                                text = "Rp. $totalText",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    HorizontalLineCard()
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "Total",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 37.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            val netTotalText = "Rp. ${viewModel.calculateNetTotalDay()}"
                            Text(
                                text = netTotalText,
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
//                    Column (
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(end = 20.dp),
//                        horizontalAlignment = Alignment.End
//                    ) {
//                        Button(
//                            onClick = {
//                                val day = viewModel.day.value
//                                val route = "${Route.DETAIL}/$day"
//                                Log.d("Navigation", "Navigating to: $route")
//                                navController.navigate(route)
//                            },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Neutral51,
//                                contentColor = Color.White
//                            )
//                        ) {
//                            Text(text = "Detail")
//                        }
//                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
package com.example.myapplication.ui.presentation.statistic.component

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
import com.example.myapplication.ui.presentation.detailTransaksi.DetailTransaksiViewModel
import com.example.myapplication.ui.presentation.order.component.HorizontalLine
import com.example.myapplication.ui.presentation.statistic.StatisticViewModel
import com.example.myapplication.ui.theme.Neutral51
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary
import com.example.myapplication.ui.theme.primary2
import kotlinx.coroutines.CoroutineScope

@Composable
fun Content(
    navController: NavController,
    viewModel: StatisticViewModel,
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
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(primary2),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 7.dp)
                ) {
                    Text(
                        text = "Bulan  :",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "${viewModel.month.value} - ${viewModel.year.value}",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

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
            ) {
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, top = 7.dp)
                ) {
                    Text(
                        text = "Penjualan Perbulan (belum dipotong)",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    if (month.isEmpty()) {
                        Text(
                            text = "Silakan pilih bulan terlebih dahulu",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp,
                            color = Color.Red
                        )
                    }else{
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
                                val totalText = if (viewModel.countMonth.value != null) viewModel.countMonth.value.toString() else "0"
                                Text(
                                    text = totalText,
                                    style = Type.text2xsMedium(),
                                    fontSize = 12.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Text(
                                text = "Commision",
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
                                val totalText = if (viewModel.commissionMonth.value != null) viewModel.commissionMonth.value.toString() else "0"
                                Text(
                                    text = "Rp. $totalText",
                                    style = Type.text2xsMedium(),
                                    fontSize = 12.sp)
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
                            Box (
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 37.dp),
                                contentAlignment = Alignment.CenterEnd
                            ){
                                val totalText = if (viewModel.chargeBulan.value != null) viewModel.chargeBulan.value.toString() else "0"
                                Text(
                                    text = "Rp. $totalText",
                                    style = Type.text2xsMedium(),
                                    fontSize = 12.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Text(
                                text = "Total Modal",
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
                                val totalText = if (viewModel.modal.value != null) viewModel.modal.value.toString() else "0"
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
                                val totalText = if (viewModel.totalMonth.value != null) viewModel.totalMonth.value.toString() else "0"
                                Text(
                                    text = "Rp. $totalText",
                                    style = Type.text2xsMedium(),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(primary2),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 7.dp)
                ) {
                    Text(
                        text = "Metode Pembayaran",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "CASH",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Box (
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 40.dp),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            Spacer(modifier = Modifier.width(1.dp))
                            val cashText = if (paymentMethodTotals?.get("CASH") != null) paymentMethodTotals!!["CASH"].toString() else "0"
                            Text(
                                text = "Rp. $cashText",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "QRIS",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Box (
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 40.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Spacer(modifier = Modifier.width(1.dp))
                            val cashText =
                                if (paymentMethodTotals?.get("QRIS") != null) paymentMethodTotals!!["QRIS"].toString() else "0"
                            Text(
                                text = "Rp. $cashText",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "E-WALLET",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Box (
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 40.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Spacer(modifier = Modifier.width(1.dp))
                            val cashText =
                                if (paymentMethodTotals?.get("E-WALLET") != null) paymentMethodTotals!!["E-WALLET"].toString() else "0"
                            Text(
                                text = "Rp. $cashText",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(primary2),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 7.dp)
                ) {
                    Text(
                        text = "Total Keuntungan",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "Pendapatan",
                            style = Type.text2xsMedium(),
                            fontSize = 12.sp
                        )
                        Box (
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 40.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Spacer(modifier = Modifier.width(1.dp))
                            val cashText =
                            Text(
                                text = "Rp. ${viewModel.calculateNetTotalMonth()}",
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


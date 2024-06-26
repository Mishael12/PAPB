package com.example.myapplication.ui.presentation.detailTransaksi.component

import ShowDatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.myapplication.data.Resource
import com.example.myapplication.model.Order
import com.example.myapplication.ui.presentation.detailTransaksi.DetailTransaksiViewModel
import com.example.myapplication.ui.presentation.statistic.StatisticViewModel
import com.example.myapplication.ui.theme.Neutral51
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary
import com.example.myapplication.ui.theme.primary2
import kotlinx.coroutines.CoroutineScope

@Composable
fun DetailContent(
    navController: NavController,
    scope: CoroutineScope,
    viewModel: DetailTransaksiViewModel,
) {
    LaunchedEffect(viewModel.day.value) {
        viewModel.fetchItemsByDay(viewModel.day.value)
    }

    val items by viewModel.items

    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        ShowDatePickerDialog(
            context = context,
            onDateSelected = { day, month, year ->
                viewModel.onChangeDay(day.toString())
                viewModel.onChangeMonth(month.toString())
                viewModel.onChangeYear(year.toString())

                showDatePicker = false
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(primary)
    ) {
        item {
            Row {
                Button(
                    onClick = { showDatePicker = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Neutral51,
                        contentColor = Color.White
                    )
                ) {
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

        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                colors = CardDefaults.cardColors(primary2),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 7.dp)
                ) {
                    Text(
                        text = "Pendapatan Perhari",
                        style = Type.displayXsSemiBold(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Text(
                            text = "ID",
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
                                text = item.idBarang,
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    Row {
                        Text(
                            text = "Nama",
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
                                text = item.nama,
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    Row {
                        Text(
                            text = "Item terjual",
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
                                text = item.jumlah.toString(),
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

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
                            Text(
                                text = item.total.toString(),
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

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
                            Text(
                                text = item.commission.toString(),
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

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
                            Text(
                                text = item.charge.toString(),
                                style = Type.text2xsMedium(),
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Button(
                            onClick = {
                                viewModel.deleteItem(item.idBarang)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Neutral51,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Remove")
                        }
                    }
                }
            }
        }
    }
}










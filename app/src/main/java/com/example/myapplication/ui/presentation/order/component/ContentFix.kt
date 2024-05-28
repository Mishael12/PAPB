package com.example.myapplication.ui.presentation.order.component


import ShowDatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ButtonType
import com.example.myapplication.data.Resource
import com.example.myapplication.ui.presentation.component.CustomButton
import com.example.myapplication.ui.presentation.component.CustomTextField
import com.example.myapplication.ui.presentation.order.OrderViewModel
import com.example.myapplication.ui.theme.Neutral51
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FixContent(
    viewModel: OrderViewModel,
    scope: CoroutineScope,
    context: Context
){
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedMetode by remember { mutableStateOf("") }

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

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
            .padding(20.dp)
    ){

        item {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Order Makanan",
                color = Color.Black,
                style = Type.textXlBold(),
                fontSize = 17.sp
            )
            Text(
                text = "Masukan Makanan yang sudah dipesan",
                color = Color.Black,
                style = Type.text2xsSemiBold(),
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Tanggal Pembelian",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            Row {
                Button(onClick = { showDatePicker = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Neutral51,
                        contentColor = Color.White)) {
                    Text(text = "Pilih Tanggal")
                }
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text(text = "Day: ${viewModel.day.value}")
                    Text(text = "Month: ${viewModel.month.value}")
                    Text(text = "Year: ${viewModel.year.value}")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Nama Makanan",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            CustomTextField(
                text = viewModel.namaBarang.value,
                placeholder = "",
                onValueChange = {
                    viewModel.onChangeNamaBarang(it)
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Deskripsi Tambahan",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            CustomTextField(
                text = viewModel.deskripsi.value ,
                placeholder = "",
                onValueChange = {
                    viewModel.onChangeDeskripsi(it)
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Harga dari Makanan",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            CustomTextField(
                text =
                if (viewModel.harga.value.toString() == "null"){
                    ""
                }else{
                    viewModel.harga.value.toString()
                },
                placeholder = "",
                isNumeric = true,
                onValueChange = {
                    if (it == ""){
                        viewModel.onChangeHarga(null)
                    }else{
                        viewModel.onChangeHarga(it.toLong())
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Jumlah",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            CustomTextField(
                text =
                if (viewModel.jumlah.value.toString() == "null"){
                    ""
                }else{
                    viewModel.jumlah.value.toString()
                },
                placeholder = "",
                onValueChange = {
                    if (it == ""){
                        viewModel.onChangeJumlah(null)
                    }else{
                        viewModel.onChangeJumlah(it.toLong())
                    }
                },
                isNumeric = true)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Commission",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            CustomTextField(
                text =
                if (viewModel.commission.value.toString() == "null"){
                    ""
                }else{
                    viewModel.commission.value.toString()
                },
                placeholder = "",
                onValueChange = {
                    if (it == ""){
                        viewModel.onChangeCommission(null)
                    }else{
                        viewModel.onChangeCommission(it.toLong())
                    }
                },
                isNumeric = true)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Charge",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            CustomTextField(
                text =
                if (viewModel.charge.value.toString() == "null"){
                    ""
                }else{
                    viewModel.charge.value.toString()
                },
                placeholder = "",
                onValueChange = {
                    if (it == ""){
                        viewModel.onChangeCharge(null)
                    }else{
                        viewModel.onChangeCharge(it.toLong())
                    }
                },
                isNumeric = true)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Metode Pembayaran",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            Column {
                PaymentOption(
                    label = "QRIS",
                    isSelected = selectedMetode == "QRIS",
                    onOptionSelected = {
                        selectedMetode = "QRIS"
                        viewModel.onChangeMetode("QRIS")
                    }
                )
                PaymentOption(
                    label = "CASH",
                    isSelected = selectedMetode == "CASH",
                    onOptionSelected = {
                        selectedMetode = "CASH"
                        viewModel.onChangeMetode("CASH")
                    }
                )
                PaymentOption(
                    label = "E-WALLET",
                    isSelected = selectedMetode == "E-WALLET",
                    onOptionSelected = {
                        selectedMetode = "E-WALLET"
                        viewModel.onChangeMetode("E-WALLET")
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CustomButton(
                    onClick = {
                        if (viewModel.checkValid(context)) {
                            scope.launch {
                                viewModel.addBarang(context).collect {
                                    when (it) {
                                        is Resource.Loading -> {
                                            viewModel.setLoading(true)
                                        }

                                        is Resource.Success -> {
                                            viewModel.setDialog(true)
                                            viewModel.resetFields()
                                            selectedMetode = ""
                                            Toast.makeText(context, "Data berhasil ditambah", Toast.LENGTH_SHORT).show()
                                        }

                                        is Resource.Error -> {
                                            viewModel.setLoading(false)
                                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }
                                }
                            }
                        }
                    },
                    text = "Kirim",
                    type = ButtonType.LARGE
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun PaymentOption(
    label: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        androidx.compose.material3.Checkbox(
            checked = isSelected,
            onCheckedChange = { onOptionSelected() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = Color.Black,
            style = Type.text2xsMedium()
        )
    }
}
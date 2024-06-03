package com.example.myapplication.ui.presentation.modal.component

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ButtonType
import com.example.myapplication.data.Resource
import com.example.myapplication.ui.presentation.component.CustomButton
import com.example.myapplication.ui.presentation.component.CustomTextField
import com.example.myapplication.ui.presentation.modal.ModalViewModel
import com.example.myapplication.ui.theme.Neutral51
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ModalContent(
    viewModel: ModalViewModel,
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
                text = "Modal",
                color = Color.Black,
                style = Type.textXlBold(),
                fontSize = 17.sp
            )
            Text(
                text = "Masukan Jumlah Modal yang diperlukan",
                color = Color.Black,
                style = Type.text2xsSemiBold(),
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Tanggal Pemasukan Modal",
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
                text = "Modal",
                color = Color.Black,
                style = Type.text2xsMedium()
            )
            CustomTextField(
                text =
                if (viewModel.modal.value.toString() == "null"){
                    ""
                }else{
                    viewModel.modal.value.toString()
                },
                placeholder = "",
                onValueChange = {
                    if (it == ""){
                        viewModel.onChangeModal(null)
                    }else{
                        viewModel.onChangeModal(it.toLong())
                    }
                },
                isNumeric = true)
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
                                viewModel.addModal(context).collect {
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
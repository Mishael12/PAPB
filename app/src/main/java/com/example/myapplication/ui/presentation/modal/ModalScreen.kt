package com.example.myapplication.ui.presentation.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.presentation.modal.component.ModalContent
import com.example.myapplication.ui.presentation.modal.component.ModalHeader
import com.example.myapplication.ui.presentation.order.component.HorizontalLine
import com.example.myapplication.ui.theme.primary

@Composable
fun ModalScreen(
    navController: NavController,
    viewModel: ModalViewModel = hiltViewModel()
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
    ){
        ModalHeader(navController = navController)
        Column {
            Spacer(modifier = Modifier.height(100.dp))
            HorizontalLine()
            ModalContent(viewModel, scope, context)
        }
    }
}
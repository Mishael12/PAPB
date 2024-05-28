package com.example.myapplication.ui.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.presentation.menu.component.HeaderMenu
import com.example.myapplication.ui.presentation.menu.component.MenuForm
import com.example.myapplication.ui.theme.Type
import com.example.myapplication.ui.theme.primary
import com.example.myapplication.ui.theme.primary2

@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel = hiltViewModel()
){
    val userData by viewModel.userData

    LaunchedEffect(key1 = true) {
        viewModel.getUser()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
    ){
        AsyncImage(
            model = R.drawable.image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth())
        AsyncImage(
            model = R.drawable.menu,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth())
        Column (
            modifier = Modifier
                .align(Alignment.TopStart)
        ){
            Spacer(modifier = Modifier.height(57.dp))
            HeaderMenu(userData.item!!)
            Spacer(modifier = Modifier.height(30.dp))
            Box (
                modifier = Modifier
                    .background(
                        primary2,
                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                    )
                    .fillMaxSize()

            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    AsyncImage(
                        model = R.drawable.line,
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .width(60.dp))
                    MenuForm(navController)
                }
            }
        }

    }
}
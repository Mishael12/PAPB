package com.example.myapplication.ui.presentation.menu.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ManageHistory
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ButtonType
import com.example.myapplication.Route
import com.example.myapplication.data.Resource
import com.example.myapplication.ui.presentation.component.CustomButton
import com.example.myapplication.ui.presentation.menu.MenuViewModel
import com.example.myapplication.ui.theme.primary
import com.example.myapplication.ui.theme.primary2

@Composable
fun MenuForm(
    navController: NavController
){
    var manageMenuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
    Box {
        CustomButton(
            onClick = {
                manageMenuExpanded = true
            },
            text = "MANAGE     ",
            type = ButtonType.SMALL,
            imageVector = Icons.Filled.ArrowDropDown,
            modifier = Modifier
        )
        DropdownMenu(
            expanded = manageMenuExpanded,
            onDismissRequest = { manageMenuExpanded = false },
            modifier = Modifier.background(primary).padding(12.dp),
        ) {
            CustomButton(
                onClick = {
                    navController.navigate(Route.MANAGE)
                },
                text = "Manage         ",
                type = ButtonType.SMALL,
                imageVector = Icons.Filled.ManageHistory, // Pass the icon here
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(15.dp))
            CustomButton(
                onClick = {
                    navController.navigate(Route.MODAL)
                },
                text = "ADD MODAL       ",
                type = ButtonType.SMALL,
                imageVector = Icons.Filled.ManageHistory, // Pass the icon here
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(15.dp))
            CustomButton(
                onClick = {
                    navController.navigate(Route.PAYMENT)
                },
                text = "Info Payment",
                type = ButtonType.SMALL,
                imageVector = Icons.Filled.Payment, // Pass the icon here
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(15.dp))
            CustomButton(
                onClick = {
                    navController.navigate(Route.DETAIL)
                },
                text = "Detail              ",
                type = ButtonType.SMALL,
                imageVector = Icons.Filled.Details, // Pass the icon here
                modifier = Modifier
            )
        }
    }
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
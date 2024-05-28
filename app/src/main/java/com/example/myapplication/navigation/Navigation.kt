package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.myapplication.Route
import com.example.myapplication.ui.presentation.detailTransaksi.DetailTransaksi
import com.example.myapplication.ui.presentation.intro.Intro
import com.example.myapplication.ui.presentation.login.LoginScreen
import com.example.myapplication.ui.presentation.menu.MenuScreen
import com.example.myapplication.ui.presentation.order.OrderScreen
import com.example.myapplication.ui.presentation.register.RegisterScreen
import com.example.myapplication.ui.presentation.statistic.StatisticScreen

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Route.LOGIN){
        composable(Route.REGISTER) {
            RegisterScreen(navController)
        }
        composable(Route.LOGIN){
            LoginScreen(navController)
        }
        composable(Route.INTRO){
            Intro(navController)
        }
        composable(Route.MAIN){
            MenuScreen(navController)
        }
        composable(Route.ORDER){
            OrderScreen(navController)
        }
        composable(Route.TRANSAKSI){
            StatisticScreen(navController)
        }
        composable("${Route.DETAIL}/{day}") { backStackEntry ->
            val day = backStackEntry.arguments?.getString("day") ?: ""
            DetailTransaksi(navController, day)
        }
    }
}
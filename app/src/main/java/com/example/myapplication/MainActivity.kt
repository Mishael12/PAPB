package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.presentation.login.LoginScreen
import com.example.myapplication.ui.presentation.register.RegisterScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import kotlin.io.path.ExperimentalPathApi

@OptIn(ExperimentalPathApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currPage = backStackEntry?.destination?.route
            Navigation(navController = navController)

        }
    }
}

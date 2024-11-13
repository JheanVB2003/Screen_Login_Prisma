package com.example.screenlogin

import LoginViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screenlogin.model.RegisterViewModel
import com.example.screenlogin.screens.loginScreen
import com.example.screenlogin.screens.singUpScreen

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelRegister: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModelRegister = ViewModelProvider(this).get(RegisterViewModel::class.java)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "Login") {
                    composable(route = "Login") {
                        loginScreen(navController = navController, viewModel)
                    }

                    composable(route = "SingUp") {
                        singUpScreen(navController = navController, viewModelRegister)
                    }
                }
        }
    }
}

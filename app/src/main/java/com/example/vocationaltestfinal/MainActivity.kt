package com.example.vocationaltestfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocationaltestfinal.chat.ChatRoute
import com.example.vocationaltestfinal.chat_agropecuaria.AgroExpertRoute
import com.example.vocationaltestfinal.ui.theme.VocationalTestFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VocationalTestFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "bakingScreen") {
                        composable("bakingScreen") { BakingScreen(navController) }
                        composable("vocationalTest") { VocationalTestScreen(navController) }
                        composable("consejos") { ConsejosScreen() }
                        composable("otrosTest") { OtrosTestScreen() }
                        composable("cuenta") { CuentaScreen(navController) }
                        composable("chat") { ChatRoute() }
                        composable("chatagroexpert") { AgroExpertRoute() }

                    }
                }
            }
        }
    }
}





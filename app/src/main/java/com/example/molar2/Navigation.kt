package com.example.molar2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable

fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "metals") {
        composable("metals") { MetalsScreen(navController) }
        composable("li") { LiScreen(navController) } // 🌟 This matches the Li.kt screen
        composable("element_detail/{symbol}") { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol") ?: ""

        }
    }
}

package com.droid.ui.lampstore.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.droid.ui.lampstore.screens.HomeScreen
import com.droid.ui.lampstore.viewmodel.LampStoreViewModel

/**
 * This function creates the navigation graph for the DashboardScreen.
 * It takes the dashboardNavController, the LampStoreViewModel, the parentNavController, and the padding as parameters.
 */
@Composable
fun DashboardNavGraph(dashboardNavController : NavHostController, viewModel : LampStoreViewModel, parentNavController: NavController, padding : PaddingValues){
    NavHost(navController = dashboardNavController, startDestination = "home") {
        composable("home") {
            HomeScreen(parentNavController, viewModel, padding)
        }
        composable("search") {
        }
        composable("cart") {
        }
        composable("notification") {
        }
        composable("profile") {
        }
    }
}
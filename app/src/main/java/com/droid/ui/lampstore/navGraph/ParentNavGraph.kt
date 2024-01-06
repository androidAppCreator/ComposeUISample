package com.droid.ui.lampstore.navGraph

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.droid.ui.lampstore.screens.DashboardScreen
import com.droid.ui.lampstore.screens.LampDetailScreen
import com.droid.ui.lampstore.screens.OnBoardingScreen
import com.droid.ui.lampstore.viewmodel.LampStoreViewModel

/**
 * This function creates the parent navigation graph for the Activity.
 * @param parentNavController nav controller for the main flow.
 * @param viewModel common LampStoreViewModel object.
 * @param context context of the current screen.
 */
@Composable
fun ParentNavGraph(parentNavController: NavHostController, viewModel: LampStoreViewModel, context: Context) {
    // The startDestination parameter is used to specify which screen is shown when the Activity is first created.
    // The OnBoardingScreen is shown if the user has not visited the onboarding flow yet. If the user has visited the onboarding flow, the dashboard screen is shown
    NavHost(navController = parentNavController, startDestination = if (viewModel.isOnBoardingFlowVisited(context)) "dashboard" else "onboarding") {
        composable("onboarding") {
            OnBoardingScreen(parentNavController, viewModel)
        }
        // The DashboardScreen is shown if the user has visited the onboarding flow.
        composable("dashboard") {
            DashboardScreen(parentNavController, viewModel)
        }
        // The LampDetailScreen is shown when the user clicks on a lamp card in the DashboardScreen.
        composable("detail") {
            LampDetailScreen(parentNavController, viewModel)
        }
    }
}
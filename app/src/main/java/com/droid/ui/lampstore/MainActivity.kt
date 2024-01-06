package com.droid.ui.lampstore

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.droid.ui.lampstore.navGraph.ParentNavGraph
import com.droid.ui.lampstore.ui.theme.DarkCharcoalGrey
import com.droid.ui.lampstore.ui.theme.LampStoreTheme
import com.droid.ui.lampstore.viewmodel.LampStoreViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LampStoreTheme {
                // This gets an instance of the LampStoreViewModel.
                val viewModel : LampStoreViewModel = viewModel()

                // This gets an instance of the SystemUiController.
                val systemUiController = rememberSystemUiController()

                // This sets the status bar color to DarkCharcoalGrey and disables dark icons.
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = DarkCharcoalGrey,
                        darkIcons = false
                    )
                }
                // This gets an instance of the NavController.
                val parentNavController = rememberNavController()
                ParentNavGraph(parentNavController = parentNavController, viewModel = viewModel, this)
            }
        }
    }
}
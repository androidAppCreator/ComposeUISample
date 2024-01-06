package com.droid.ui.lampstore.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droid.ui.lampstore.model.BottomNavItem
import com.droid.ui.lampstore.navGraph.DashboardNavGraph
import com.droid.ui.lampstore.ui.theme.DarkCharcoalGrey
import com.droid.ui.lampstore.ui.theme.Orange
import com.droid.ui.lampstore.viewmodel.LampStoreViewModel

/**
 * This function creates the DashboardScreen.
 * @param parentNavController parent navController to navigate between the screen.
 * @param viewModel common LampStoreViewModel object.
 */
@Composable
fun DashboardScreen(
    parentNavController: NavController,
    viewModel: LampStoreViewModel
) {
    // The selectedItemIndex variable is used to track the selected item in the bottom navigation bar.
    var selectedItemIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    val dashboardNavController = rememberNavController()

    // The Scaffold() function is used to create a scaffold that contains the bottom navigation bar and the content of the screen.
    Scaffold(bottomBar = {
        BottomNavigationBar(
            items = viewModel.getBottomNavigationItem(),
            selectedItemIndex,
            dashboardNavController
        )
    }, content = { padding ->
        DashboardNavGraph(
            dashboardNavController = dashboardNavController,
            viewModel = viewModel,
            parentNavController = parentNavController,
            padding
        )
    })
}

/**
 * This function creates the bottom navigation bar.
 * The NavigationBarItem is item with image and the 3rd item i.e cart it has a round orange background.
 * @param items navigationBarItem list to display the content in  NavigationBar
 * @param selectedItemIndex selected item in the bottom navigation bar.
 * @param dashboardNavController navController to navigate between the dashboard screens.
 */
@Composable
fun BottomNavigationBar(
    items: ArrayList<BottomNavItem>,
    selectedItemIndex: MutableState<Int>,
    dashboardNavController: NavController
) {
    // The NavigationBar() function is used to create the bottom navigation bar.
    NavigationBar(containerColor = DarkCharcoalGrey, contentColor = Color.White) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = if (item.label == "Cart") Modifier
                    .padding(
                        horizontal = 9.dp,
                        vertical = 16.dp
                    )
                    .clip(CircleShape)
                    .background(Orange) else Modifier,
                selected = selectedItemIndex.value == index,
                onClick = {
                    selectedItemIndex.value = index
//                    dashboardNavController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex.value) {
                            item.selectedIcon
                        } else item.unSelectedIcon,
                        contentDescription = item.label,
                        tint = Color.White
                    )
                },
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(indicatorColor = if (item.label == "Cart") Orange else DarkCharcoalGrey)
            )
        }
    }
}
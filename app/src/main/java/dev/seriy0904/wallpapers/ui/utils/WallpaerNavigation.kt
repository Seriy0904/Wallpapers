package dev.seriy0904.wallpapers.ui.utils

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

const val TOPLIST_ROUTE = "toplist"
const val LATEST_ROUTE = "latest"
class WallpaerNavigationActions(navController:NavHostController){
    val navigateToTopList: () -> Unit = {
        navController.navigate(TOPLIST_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToLatest: () -> Unit = {
        navController.navigate(LATEST_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
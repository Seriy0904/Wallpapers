package dev.seriy0904.wallpapers.ui.graphs

import android.util.Log
import androidx.navigation.NavHostController

const val TOPLIST_ROUTE = "toplist"
const val LATEST_ROUTE = "latest"
const val SELECTED_IMAGE_ROUTE = "details"

class WallpaerNavigationActions(navController: NavHostController) {
    val navigateToTopList: () -> Unit = {
        navController.navigate(TOPLIST_ROUTE) {
            popUpTo(TOPLIST_ROUTE) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        Log.d("MyTag","back Queue${ navController.backQueue.map { it.destination.route }.toList() }")
    }
    val navigateToLatest: () -> Unit = {
        navController.navigate(LATEST_ROUTE) {
            popUpTo(TOPLIST_ROUTE) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        Log.d("MyTag","back Queue${ navController.backQueue.map { it.destination.route }.toList() }")
    }

    val navigateToSelectedImage: (id: String) -> Unit = { id ->
        navController.navigate("$SELECTED_IMAGE_ROUTE/$id")
        Log.d("MyTag","back Queue${ navController.backQueue.map { it.destination.route }.toList() }")
    }
}
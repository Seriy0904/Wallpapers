package dev.seriy0904.wallpapers.ui.graphs

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
    }
    val navigateToLatest: () -> Unit = {
        navController.navigate(LATEST_ROUTE) {
            popUpTo(TOPLIST_ROUTE) {
                saveState = true
//                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToSelectedImage: (id: String) -> Unit = { id ->
        navController.navigate("$SELECTED_IMAGE_ROUTE/$id")
    }
}
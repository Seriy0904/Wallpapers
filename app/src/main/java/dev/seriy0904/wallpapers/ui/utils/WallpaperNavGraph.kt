package dev.seriy0904.wallpapers.ui.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.ui.details.ImageDetailsScreen
import dev.seriy0904.wallpapers.ui.latest.LatestScreen
import dev.seriy0904.wallpapers.ui.viewModel.ListViewModel
import dev.seriy0904.wallpapers.ui.viewModel.ListViewModelProvider
import dev.seriy0904.wallpapers.ui.toplist.ToplistScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = TOPLIST_ROUTE,
    retrofit: WallhavenApi,
    navigationActions: WallpaerNavigationActions
) {
    val viewModel: ListViewModel = viewModel(factory = ListViewModelProvider(retrofit))
    viewModel.firstTopListLoad()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = TOPLIST_ROUTE) {
            ToplistScreen(viewModel,navigationActions)
        }
        composable(route = LATEST_ROUTE) {
            LatestScreen(retrofit)
        }
        composable(
            "$SELECTED_ROUTE/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.StringType })
        ) {
            ImageDetailsScreen(imageId = it.arguments?.getString( "imageId")?:"", viewModel = viewModel)
        }
    }
}
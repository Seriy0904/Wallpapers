package dev.seriy0904.wallpapers.ui.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.ui.latest.LatestScreen
import dev.seriy0904.wallpapers.ui.toplist.TopListViewModel
import dev.seriy0904.wallpapers.ui.toplist.TopListViewModelProvider
import dev.seriy0904.wallpapers.ui.toplist.ToplistScreen

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String = TOPLIST_ROUTE, retrofit: WallhavenApi) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = TOPLIST_ROUTE) {
            val viewModel: TopListViewModel = viewModel(factory = TopListViewModelProvider(retrofit))
            ToplistScreen(viewModel)
        }
        composable(route = LATEST_ROUTE) {
            LatestScreen(retrofit)
        }
    }
}
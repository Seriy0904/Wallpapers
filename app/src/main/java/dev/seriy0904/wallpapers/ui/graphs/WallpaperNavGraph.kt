package dev.seriy0904.wallpapers.ui.graphs

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.ui.imageDetails.ImageDetailsScreen
import dev.seriy0904.wallpapers.ui.imageDetails.ImageDetailsViewModel
import dev.seriy0904.wallpapers.ui.imageDetails.ImageDetailsViewModelProvider
import dev.seriy0904.wallpapers.ui.latest.LatestListViewModel
import dev.seriy0904.wallpapers.ui.latest.LatestListViewModelProvider
import dev.seriy0904.wallpapers.ui.latest.LatestScreen
import dev.seriy0904.wallpapers.ui.toplist.TopistViewModel
import dev.seriy0904.wallpapers.ui.toplist.ToplistScreen
import dev.seriy0904.wallpapers.ui.toplist.ToplistViewModelProvider

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = TOPLIST_ROUTE,
    retrofit: WallhavenApi,
    navigationActions: WallpaerNavigationActions
) {
    val topListViewModel: TopistViewModel = viewModel(factory = ToplistViewModelProvider(retrofit))
    val latestListViewModel: LatestListViewModel = viewModel(factory = LatestListViewModelProvider(retrofit))
    val imageDetailsViewModel: ImageDetailsViewModel = viewModel(factory = ImageDetailsViewModelProvider(retrofit))
    topListViewModel.firstTopListLoad()
    latestListViewModel.firstLatesListLoad()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = TOPLIST_ROUTE) {
            ToplistScreen(topListViewModel,navigationActions)
        }
        composable(route = LATEST_ROUTE) {
            LatestScreen(latestListViewModel,navigationActions)
        }
        composable(
            "$SELECTED_IMAGE_ROUTE/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.StringType })
        ) {
            ImageDetailsScreen(imageId = it.arguments?.getString( "imageId")?:"", viewModel = imageDetailsViewModel,navigationActions = navigationActions)
        }
    }
}
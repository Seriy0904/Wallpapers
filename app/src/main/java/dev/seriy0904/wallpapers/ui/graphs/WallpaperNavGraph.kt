package dev.seriy0904.wallpapers.ui.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.gson.Gson
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.FiltersModel
import dev.seriy0904.wallpapers.ui.customList.CustomListScreen
import dev.seriy0904.wallpapers.ui.customList.CustomListViewModel
import dev.seriy0904.wallpapers.ui.customList.CustomListViewModelProvider
import dev.seriy0904.wallpapers.ui.imageDetails.ImageDetailsScreen
import dev.seriy0904.wallpapers.ui.imageDetails.ImageDetailsViewModel
import dev.seriy0904.wallpapers.ui.imageDetails.ImageDetailsViewModelProvider

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.CustomList.route,
    retrofit: WallhavenApi,
    navigationActions: WallpaperNavigationActions,
    openDrawer: () -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = startDestination) {

        composable(
            Screen.CustomList.route,
            arguments = listOf(navArgument("filter") {
                type = NavType.StringType
                defaultValue = ""
            }),
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { 2000 }
                )
            }
        ) {
            val customListViewModel: CustomListViewModel =
                viewModel(factory = CustomListViewModelProvider(retrofit))
            val argFilter = it.arguments?.getString("filter") ?: ""
            val filterModel = Gson().fromJson(argFilter, FiltersModel::class.java) ?: FiltersModel()
            customListViewModel.customListLoad(filterModel)
            CustomListScreen(customListViewModel, navigationActions, filterModel, openDrawer)

        }
        composable(
            Screen.ImageDetails.route,
            arguments = listOf(navArgument("imageId") { type = NavType.StringType }),
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { 2000 }
                )
            }
        ) {
            val imageId = it.arguments?.getString("imageId")
            imageId?.let {
                val imageDetailsViewModel: ImageDetailsViewModel =
                    viewModel(factory = ImageDetailsViewModelProvider(retrofit))
                imageDetailsViewModel.loadImageInfo(imageId)
                ImageDetailsScreen(
                    imageId = imageId,
                    viewModel = imageDetailsViewModel,
                    navigationActions = navigationActions
                )
            }
        }
    }
}
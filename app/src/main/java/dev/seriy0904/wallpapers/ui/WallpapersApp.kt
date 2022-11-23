package dev.seriy0904.wallpapers.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.ui.graphs.Screen
import dev.seriy0904.wallpapers.ui.graphs.SetupNavGraph
import dev.seriy0904.wallpapers.ui.graphs.WallpaperNavigationActions
import dev.seriy0904.wallpapers.ui.theme.WallpapersTheme
import dev.seriy0904.wallpapers.ui.utils.AppDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Wallpapers(retrofit: WallhavenApi) {
    WallpapersTheme() {
        val navController = rememberAnimatedNavController()
        val navigationActions = remember(navController) {
            WallpaperNavigationActions(navController)
        }
        val items = listOf(
            Screen.CustomList.route
        )
        val coroutineScope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var appDrawerRoute by remember { mutableStateOf(items.first()) }
        val route = navBackStackEntry?.destination?.route ?: items.first()
        val currentRoute = if (items.contains(route)) route else appDrawerRoute
        if (currentRoute != appDrawerRoute) appDrawerRoute = currentRoute
        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawer(
                    navigationActions = navigationActions,
                    currentRoute = currentRoute,
                    items = items
                ) {
                    appDrawerRoute = it
                    coroutineScope.launch { drawerState.close() }
                }
            }
        ) {
            SetupNavGraph(
                navController = navController,
                retrofit = retrofit,
                navigationActions = navigationActions,
                openDrawer = { coroutineScope.launch { drawerState.open() } }
            )
        }
    }
}


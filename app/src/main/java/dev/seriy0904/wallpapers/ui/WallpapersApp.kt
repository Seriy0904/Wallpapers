package dev.seriy0904.wallpapers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.seriy0904.wallpapers.R
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.ui.graphs.Screen
import dev.seriy0904.wallpapers.ui.graphs.SetupNavGraph
import dev.seriy0904.wallpapers.ui.graphs.WallpaperNavigationActions
import dev.seriy0904.wallpapers.ui.theme.WallpapersTheme
import dev.seriy0904.wallpapers.ui.utils.AppDrawer
import kotlinx.coroutines.launch

@Composable
fun Wallpapers(retrofit: WallhavenApi) {
    WallpapersTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            WallpaperNavigationActions(navController)
        }
        val items = listOf(
            Screen.CustomList.route
        )
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var appDrawerRoute by remember { mutableStateOf(items.first()) }
        val route = navBackStackEntry?.destination?.route?: items.first()
        val currentRoute = if(items.contains(route)) route else appDrawerRoute
        if(currentRoute!=appDrawerRoute) appDrawerRoute = currentRoute
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = when  {
                                route.startsWith(Screen.CustomList.route) -> stringResource(id = R.string.custom_list_label)
                                route.startsWith(Screen.ImageDetails.route) -> stringResource(id = R.string.image_details_label)
                                else -> "Wallpapers"
                            }
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Open drawer menu"
                            )
                        }
                    }, backgroundColor = MaterialTheme.colors.primaryVariant,
                    contentColor = MaterialTheme.colors.onPrimary
                )
            },
            drawerContent = {
                AppDrawer(
                    navigationActions = navigationActions,
                    currentRoute = currentRoute,
                    items = items
                ) {
                    appDrawerRoute = it
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            }
        ) { scaffoldPadding ->
            Box(Modifier.padding(scaffoldPadding)) {
                SetupNavGraph(
                    navController = navController,
                    retrofit = retrofit,
                    navigationActions = navigationActions
                )
            }
        }
    }
}


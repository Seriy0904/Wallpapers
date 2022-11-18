package dev.seriy0904.wallpapers.ui

import android.util.Log
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
import dev.seriy0904.wallpapers.ui.graphs.LATEST_ROUTE
import dev.seriy0904.wallpapers.ui.graphs.SetupNavGraph
import dev.seriy0904.wallpapers.ui.graphs.TOPLIST_ROUTE
import dev.seriy0904.wallpapers.ui.graphs.WallpaerNavigationActions
import dev.seriy0904.wallpapers.ui.theme.WallpapersTheme
import dev.seriy0904.wallpapers.ui.utils.AppDrawer
import kotlinx.coroutines.launch

@Composable
fun Wallpapers(retrofit: WallhavenApi) {
    WallpapersTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            WallpaerNavigationActions(navController)
        }
        val items = listOf(
            TOPLIST_ROUTE,
            LATEST_ROUTE
        )
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val appDrawerRoute = remember { mutableStateOf("") }
        val route = navBackStackEntry?.destination?.route?: TOPLIST_ROUTE
        val currentRoute = if(items.contains(route)) route else appDrawerRoute.value
        Log.d("MyTag", "appDrawerRoute ${ appDrawerRoute.value }")
        Log.d("MyTag", "route ${ route }")
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = when (currentRoute) {
                                TOPLIST_ROUTE -> stringResource(id = R.string.topList_label)
                                LATEST_ROUTE -> stringResource(id = R.string.latest_label)
                                else -> stringResource(id = R.string.app_name)
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
                    if(!items.contains(appDrawerRoute.value)) appDrawerRoute.value = it
                    Log.d("MyTag", "AppDrawer lambda ${ it }")
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


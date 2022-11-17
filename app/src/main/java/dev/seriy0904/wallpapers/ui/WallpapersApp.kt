package dev.seriy0904.wallpapers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.seriy0904.wallpapers.R
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.ui.theme.WallpapersTheme
import dev.seriy0904.wallpapers.ui.utils.*
import kotlinx.coroutines.launch

@Composable
fun Wallpapers(retrofit: WallhavenApi) {
    WallpapersTheme() {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            WallpaerNavigationActions(navController)
        }
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: TOPLIST_ROUTE
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
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Open drawer menu")
                        }
                    }, backgroundColor = MaterialTheme.colors.primaryVariant,
                    contentColor = MaterialTheme.colors.onPrimary
                )
            },
            drawerContent = {
                AppDrawer(
                    navigationActions = navigationActions,
                    currentRoute = currentRoute
                ) {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            }
        ) { scaffoldPadding ->
            Box(Modifier.padding(scaffoldPadding)) {
                SetupNavGraph(navController = navController,retrofit = retrofit, navigationActions = navigationActions)
            }
        }
    }
}

package dev.seriy0904.wallpapers.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import dev.seriy0904.wallpapers.R
import dev.seriy0904.wallpapers.ui.graphs.Screen
import dev.seriy0904.wallpapers.ui.graphs.WallpaperNavigationActions

@Composable
fun AppDrawer(
    navigationActions: WallpaperNavigationActions,
    currentRoute: String,
    items: List<String>,
    closeDrawer: (String) -> Unit
) {
    Column {
        Divider(
            Modifier.padding(top = 28.dp, bottom = 2.dp),
            color = MaterialTheme.colors.surface.copy(alpha = .2f)
        )
        items.forEachIndexed() { pos, it ->
            DrawerItem(
                tittle = when (it) {
                    Screen.CustomList.route -> stringResource(id = R.string.custom_list_label)
                    else -> ""
                }, it == currentRoute
            ) {
                when (it) {
                    Screen.CustomList.route -> {
                        navigationActions.navigateToCustomList()
                    }
                }
                closeDrawer(it)
            }

        }
    }
}


@Composable
fun DrawerItem(tittle: String, isSelected: Boolean, action: () -> Unit) {
    val colors = MaterialTheme.colors
    val colorBackground = if (isSelected) colors.primary.copy(alpha = 0.12f) else Color.Transparent
    Surface(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .fillMaxWidth(), color = colorBackground, shape = MaterialTheme.shapes.small
    ) {
        TextButton(onClick = action, Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                Text(text = tittle)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppDrawer() {
    AppDrawer(
        navigationActions = WallpaperNavigationActions(rememberNavController()),
        currentRoute = "toplist",
        items = listOf("Toplist", "Latest")
    ) {}
}
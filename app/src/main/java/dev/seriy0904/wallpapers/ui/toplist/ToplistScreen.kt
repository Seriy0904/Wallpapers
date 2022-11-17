package dev.seriy0904.wallpapers.ui.toplist

import androidx.compose.runtime.Composable
import dev.seriy0904.wallpapers.ui.utils.ImageList
import dev.seriy0904.wallpapers.ui.utils.WallpaerNavigationActions
import dev.seriy0904.wallpapers.ui.viewModel.ListViewModel


@Composable
fun ToplistScreen(viewModel: ListViewModel,navigationActions: WallpaerNavigationActions) {
    ImageList(viewModel){
        navigationActions.navigateToSelected(it)
    }
}
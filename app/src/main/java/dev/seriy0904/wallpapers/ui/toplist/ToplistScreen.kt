package dev.seriy0904.wallpapers.ui.toplist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dev.seriy0904.wallpapers.ui.graphs.WallpaerNavigationActions
import dev.seriy0904.wallpapers.ui.utils.ImageList


@Composable
fun ToplistScreen(viewModel: TopistViewModel, navigationActions: WallpaerNavigationActions) {
    val searchModel by viewModel.topList.observeAsState()
    ImageList(searchModel){
        navigationActions.navigateToSelectedImage(it)
    }
}
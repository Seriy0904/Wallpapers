package dev.seriy0904.wallpapers.ui.latest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dev.seriy0904.wallpapers.ui.graphs.WallpaerNavigationActions
import dev.seriy0904.wallpapers.ui.utils.ImageList

@Composable
fun LatestScreen(viewModel: LatestListViewModel, navigationActions: WallpaerNavigationActions) {
    val searchModel by viewModel.latestList.observeAsState()
    ImageList(searchModel){
        navigationActions.navigateToSelectedImage(it)
    }
}
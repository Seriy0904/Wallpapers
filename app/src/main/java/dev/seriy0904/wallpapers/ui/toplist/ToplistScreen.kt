package dev.seriy0904.wallpapers.ui.toplist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import dev.seriy0904.wallpapers.ui.utils.ImageList


@Composable
fun ToplistScreen(viewModel: TopListViewModel) {
    viewModel.updateList()
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val imageList = remember { mutableStateOf(viewModel.topList.value?.imageList?.map {  it.thumbs.small }?: listOf())}
    viewModel.topList.observe(lifecycleOwner.value) {newModel->
        imageList.value = newModel.imageList.map {  it.thumbs.small }
    }
    ImageList(imageList = imageList.value)
}
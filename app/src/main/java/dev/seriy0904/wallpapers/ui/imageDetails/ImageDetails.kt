package dev.seriy0904.wallpapers.ui.imageDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.AsyncImage
import dev.seriy0904.wallpapers.model.ImageDetailsModel
import dev.seriy0904.wallpapers.ui.graphs.WallpaerNavigationActions

@Composable
fun ImageDetailsScreen(imageId: String, viewModel: ImageDetailsViewModel, navigationActions: WallpaerNavigationActions) {
    val imageDetails:MutableState<ImageDetailsModel?> = remember { mutableStateOf(null)}
    val imageUrl = imageDetails.value?.data?.path?:""
    viewModel.loadImageInfo(imageId) {
        if(it!=null) {
            imageDetails.value = it
        }
    }
    Column (){
        AsyncImage(model = imageUrl, contentDescription = "Current Image")
    }
}
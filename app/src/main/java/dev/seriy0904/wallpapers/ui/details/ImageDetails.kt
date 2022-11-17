package dev.seriy0904.wallpapers.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import coil.compose.AsyncImage
import dev.seriy0904.wallpapers.model.ImageDetailsModel
import dev.seriy0904.wallpapers.ui.viewModel.ListViewModel

@Composable
fun ImageDetailsScreen(imageId: String, viewModel: ListViewModel) {
    val imageDetails:MutableState<ImageDetailsModel?> = remember { mutableStateOf(null)}
    val imageUrl = imageDetails.value?.data?.path?:""
    viewModel.loadImageInfo(imageId) {
        if(it!=null) {
            imageDetails.value = it
        }
    }
    Column {
        AsyncImage(model = imageUrl, contentDescription = "Current Image")
    }
}
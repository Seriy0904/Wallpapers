package dev.seriy0904.wallpapers.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.seriy0904.wallpapers.model.SearchListModel

@Composable
fun ImageList(listModel: SearchListModel?, onClickImage: (id: String) -> Unit) {
    val imageList = listModel?.dataList?.map { it.thumbs.small } ?: listOf()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        Modifier
            .padding(horizontal = 6.dp, vertical = 10.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(imageList.size) { imageIndex ->
            IconButton(onClick = {
                val clickData = listModel?.dataList?.get(imageIndex)?.id
                if (clickData != null) onClickImage(clickData)
            }) {
                SubcomposeAsyncImage(
                    model = imageList[imageIndex],
                    contentDescription = "Image from list",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
        }
    }

}
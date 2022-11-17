package dev.seriy0904.wallpapers.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.seriy0904.wallpapers.ui.viewModel.ListViewModel

@Composable
fun ImageList(viewModel: ListViewModel, onClickImage: (id: String) -> Unit) {
    val searchModel by viewModel.topList.observeAsState()
    val imageList = searchModel?.dataList?.map { it.thumbs.small } ?: listOf()
    Box(contentAlignment = Alignment.TopCenter) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            Modifier.padding(horizontal = 6.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(imageList.size) { imageIndex ->
                IconButton(onClick = {
                    val clickData = searchModel?.dataList?.get(imageIndex)?.id
                    if (clickData != null) onClickImage(clickData)
                }) {
                    AsyncImage(
                        model = imageList[imageIndex],
                        contentDescription = "Image from list",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

}
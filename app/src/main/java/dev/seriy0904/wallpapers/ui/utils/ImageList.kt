package dev.seriy0904.wallpapers.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.seriy0904.wallpapers.model.SearchListModel

@Composable
fun ImageList(listModel: SearchListModel?, onClickImage: (id: String) -> Unit) {
    val imageList = listModel?.dataList?.map { it.thumbs.small } ?: listOf()
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(128.dp),
//        Modifier
//            .padding(horizontal = 6.dp)
//            .padding(bottom = 10.dp)
//            .fillMaxSize(),
//        horizontalArrangement = Arrangement.spacedBy(6.dp),
//        verticalArrangement = Arrangement.spacedBy(6.dp)
//    ) {
//        items(imageList.size) { imageIndex ->
//            IconButton(onClick = {
//                val clickData = listModel?.dataList?.get(imageIndex)?.id
//                if (clickData != null) onClickImage(clickData)
//            }) {
//                SubcomposeAsyncImage(
//                    model = imageList[imageIndex],
//                    contentDescription = "ImageLoader from list",
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop,
//                    alignment = Alignment.Center,
//                    loading = { LinearProgressIndicator() }
//                )
//            }
//        }
//    }
    val scrollState = rememberScrollState()
    BoxWithConstraints {
        val imageCount = (maxWidth.value.toInt() / 128)
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = 6.dp)
                .padding(bottom = 10.dp),
        ) {
            for (i in imageList.indices step imageCount) {
                Row {
                    for (j in 0 until imageCount) {
                        val imageIndex = i + j
                        IconButton(
                            onClick = {
                                val clickData = listModel?.dataList?.get(imageIndex)?.id
                                if (clickData != null) onClickImage(clickData)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier.fillMaxWidth(),
                                model = imageList[imageIndex],
                                contentDescription = "ImageLoader from list",
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center,
                                loading = { LinearProgressIndicator() }
                            )
                        }
                        if (j + 1 == imageCount) break
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                }
                if (i + 1 == imageList.size - 1) break
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }


}
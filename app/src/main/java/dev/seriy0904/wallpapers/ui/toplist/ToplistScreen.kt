package dev.seriy0904.wallpapers.ui.toplist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.seriy0904.wallpapers.data.api.WallhavenApi


@Composable
fun ToplistScreen(retrofit: WallhavenApi) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "TopList")
    }
}
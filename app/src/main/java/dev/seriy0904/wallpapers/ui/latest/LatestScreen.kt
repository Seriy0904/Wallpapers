package dev.seriy0904.wallpapers.ui.latest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.seriy0904.wallpapers.data.api.WallhavenApi

@Composable
fun LatestScreen(retrofit: WallhavenApi) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Latest")
    }
}
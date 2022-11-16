package dev.seriy0904.wallpapers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.seriy0904.wallpapers.WallpapersApplications

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = (application as WallpapersApplications).getApi()
        setContent {
            Wallpapers(retrofit)
        }
    }
}
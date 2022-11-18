package dev.seriy0904.wallpapers.ui.imageDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.ImageDetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageDetailsViewModel(private val retrofit: WallhavenApi): ViewModel() {
    fun loadImageInfo(imageId: String, endLoading: (ImageDetailsModel?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.imageDetails(imageId = imageId)
            try {
                if (request.body() != null) {
                    endLoading(request.body())
                }
            } catch (e: java.lang.Exception) {
                Log.d("MyTag", e.message.toString())
            }
        }
    }
}
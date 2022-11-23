package dev.seriy0904.wallpapers.ui.imageDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.ImageDetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageDetailsViewModel(private val retrofit: WallhavenApi): ViewModel() {
    val imageDetails = MutableLiveData<ImageDetailsModel>()
    fun loadImageInfo(imageId: String, time:Int = 0) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = retrofit.imageDetails(imageId = imageId)
                if (request.body() != null) {
                    imageDetails.postValue(request.body())
                }else if(time<5){
                    loadImageInfo(imageId,time+1)
                }
            } catch (e: java.lang.Exception) {
                Log.d("MyTag", e.message.toString())
            }
        }
    }
}
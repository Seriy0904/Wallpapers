package dev.seriy0904.wallpapers.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.ImageDetailsModel
import dev.seriy0904.wallpapers.model.Meta
import dev.seriy0904.wallpapers.model.SearchListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val retrofit: WallhavenApi) : ViewModel() {
    private val _topList = MutableLiveData<SearchListModel>(null)
    val topList = _topList
    fun updateLatestList() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.search()
            try {
                if (request.isSuccessful){
                    _topList.postValue(request.body() ?: SearchListModel(listOf(), Meta()))
                }else
                    TODO("Request error")
            }catch (e:java.lang.Exception){
                Log.d("MyTag",e.message.toString())
            }
        }
    }
    fun firstTopListLoad(){
        if(_topList.value==null){
            updateTopList()
        }
    }
    fun updateTopList() {
        Log.d("MyTag","updateTopList")
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.search(sort = "toplist")
            try {
                if (request.isSuccessful){
                    _topList.postValue(request.body())
                }
            }catch (e:java.lang.Exception){
                Log.d("MyTag",e.message.toString())
            }
        }
    }



    fun loadImageInfo(imageId:String, endLoading:(ImageDetailsModel?)->Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.imageDetails(imageId = imageId)
            try {
                if (request.body()!=null){
                    endLoading(request.body())
                }
            }catch (e:java.lang.Exception){
                Log.d("MyTag",e.message.toString())
            }
        }
    }
//    companion object {
//        fun provideFactory(retrofit: WallhavenApi): ViewModelProvider.Factory = object :ViewModelProvider.Factory{
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return TopListViewModel(retrofit) as T
//            }
//        }
//    }
//    WHY THIS CODE DIDN'T WORKING??
}
package dev.seriy0904.wallpapers.ui.latest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.Meta
import dev.seriy0904.wallpapers.model.SearchListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LatestListViewModel(private val retrofit: WallhavenApi) : ViewModel() {
    private val _latestList = MutableLiveData<SearchListModel>(null)
    val latestList = _latestList

    fun firstLatesListLoad(){
        if(_latestList.value==null){
            updateLatestList()
        }
    }
    fun updateLatestList() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.search()
            try {
                if (request.isSuccessful){
                    _latestList.postValue(request.body() ?: SearchListModel(listOf(), Meta()))
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
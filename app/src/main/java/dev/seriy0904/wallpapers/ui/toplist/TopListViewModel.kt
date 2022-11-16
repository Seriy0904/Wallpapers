package dev.seriy0904.wallpapers.ui.toplist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.SearchListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopListViewModel(private val retrofit: WallhavenApi) : ViewModel() {
    private val _topList = MutableLiveData(SearchListModel(listOf()))
    val topList = _topList
    init {
        updateList()
    }
    fun updateList() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.search()
            try {
                if (request.isSuccessful){
                    _topList.postValue(request.body() ?: SearchListModel(listOf()))
                }else
                    TODO("Request error")
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
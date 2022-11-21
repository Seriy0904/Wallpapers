package dev.seriy0904.wallpapers.ui.customList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.FiltersModel
import dev.seriy0904.wallpapers.model.SearchListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomListViewModel(private val retrofit: WallhavenApi) : ViewModel() {
    val customList = MutableLiveData<SearchListModel>(null)
    fun customListLoad(filter:FiltersModel=FiltersModel()) {
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.search(querySettings = filter.tags,sort = filter.sorting, category = filter.categories)
            try {
                if (request.isSuccessful) {
                    customList.postValue(request.body())
                }
            } catch (_: java.lang.Exception) {
            }
        }
    }
}
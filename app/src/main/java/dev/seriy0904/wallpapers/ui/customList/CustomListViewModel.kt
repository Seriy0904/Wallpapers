package dev.seriy0904.wallpapers.ui.customList

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.model.FiltersModel
import dev.seriy0904.wallpapers.model.Meta
import dev.seriy0904.wallpapers.model.SearchListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomListViewModel(private val retrofit: WallhavenApi) : ViewModel() {
    fun customListLoad(filter:FiltersModel=FiltersModel(),complete:(SearchListModel)->Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val request = retrofit.search(sort = filter.sorting, category = filter.categories)
            Log.d("MyTag",request.raw().request.url.toString())
            try {
                if (request.isSuccessful) {
                    complete(request.body() ?: SearchListModel(listOf(), Meta()))
                }
            } catch (_: java.lang.Exception) {
            }
        }
    }
}
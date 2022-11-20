package dev.seriy0904.wallpapers.ui.graphs

import androidx.navigation.NavHostController
import com.google.gson.Gson
import dev.seriy0904.wallpapers.model.FiltersModel

sealed class Screen(val route: String){
    object CustomList: Screen("custom_list?filter={filter}"){
        fun passId(filter:String):String{
            return "custom_list?filter=$filter"
        }
    }
    object ImageDetails: Screen("image_details/{imageId}"){
        fun passId(id:String):String{
            return "image_details/$id"
        }
    }
}

class WallpaperNavigationActions(private val navController: NavHostController) {
    fun navigateToCustomList (searchFilter:FiltersModel=FiltersModel()){
        val codeSearchFilter = Gson().toJson(searchFilter)
        navController.navigate(Screen.CustomList.passId(codeSearchFilter)) {
            popUpTo(Screen.CustomList.route){
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToSelectedImage (id: String){
        navController.navigate(Screen.ImageDetails.passId(id))
    }
}
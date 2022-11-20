package dev.seriy0904.wallpapers.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FiltersModel(
    var categories: String = "111",
    var purity: String = "100",
    var sorting: String = "date_added",
    var order: String = "desc",
    var topRange: String = "1M",
    var atleast: String = "",
    var resolutions: String = "",
    var ratios: String = "",
    var colors: String = "",
    var page: Int = 1,
) : Parcelable

val sortingList: List<String> =
    listOf("date_added", "relevance", "random", "views", "favorites", "toplist")
val sortingDestinations = hashMapOf(
    "date_added" to "Date added",
    "relevance" to "Relevance",
    "random" to "Random",
    "views" to "Views count",
    "favorites" to "Favourites count",
    "toplist" to "Toplist",
)
val categoriesList: List<String> = listOf("General", "Anime", "People")
val purityList: List<String> = listOf("SFW","Sketchy")//TODO(ADD "NSFW" WHEN API KEY WORKING)
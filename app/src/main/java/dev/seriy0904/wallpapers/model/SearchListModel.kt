package dev.seriy0904.wallpapers.model

import com.google.gson.annotations.SerializedName

data class SearchListModel(
    @SerializedName("data")
    val imageList: List<Data>,
    val meta: Meta? = null
)

data class Data(
    val category: String,
    val colors: List<String>,
    val created_at: String,
    val dimension_x: Int,
    val dimension_y: Int,
    val favorites: Int,
    val file_size: Int,
    val file_type: String,
    val id: String,
    val path: String,
    val purity: String,
    val ratio: String,
    val resolution: String,
    val short_url: String,
    val source: String,
    val thumbs: Thumbs,
    val url: String,
    val views: Int
)

data class Meta(
    val current_page: Int,
    val last_page: Int,
    val per_page: Int,
    val query: String,
    val seed: Any,
    val total: Int
)

data class Thumbs(
    val large: String,
    val original: String,
    val small: String
)
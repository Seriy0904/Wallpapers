package dev.seriy0904.wallpapers.model

data class ImageDetailsModel(
    val `data`: DetailsData
)

data class DetailsData(
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
    val tags: List<Tag>,
    val thumbs: Thumbs,
    val uploader: Uploader,
    val url: String,
    val views: Int
)

data class Tag(
    val alias: String,
    val category: String,
    val category_id: Int,
    val created_at: String,
    val id: Int,
    val name: String,
    val purity: String
)

data class Uploader(
    val avatar: Avatar,
    val group: String,
    val username: String
)

data class Avatar(
    val `128px`: String,
    val `200px`: String,
    val `20px`: String,
    val `32px`: String
)
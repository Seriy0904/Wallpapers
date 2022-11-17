package dev.seriy0904.wallpapers.data.api

import dev.seriy0904.wallpapers.model.ImageDetailsModel
import dev.seriy0904.wallpapers.model.SearchListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val WALLHAVEN_URL:String = "https://wallhaven.cc"
interface WallhavenApi {
    @GET("/api/v1/search")
    suspend fun search(
        @Query("q") querySettings: String = "",
        @Query("page") page: Int = 1,
        @Query("sorting") sort: String = "date_added"
    ): Response<SearchListModel>

    @GET("/api/v1/w/{id}")
    suspend fun imageDetails(
        @Path("id") imageId:String
    ): Response<ImageDetailsModel>
}
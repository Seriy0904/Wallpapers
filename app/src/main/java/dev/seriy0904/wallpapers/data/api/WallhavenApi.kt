package dev.seriy0904.wallpapers.data.api

import dev.seriy0904.wallpapers.model.SearchListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val WALLHAVEN_URL:String = "https://wallhaven.cc"
interface WallhavenApi {
    @GET("/api/v1/search")
    suspend fun search(
        @Query("q") querySettings: String = "",
        @Query("page") page: Int = 1
    ): Response<SearchListModel>
}
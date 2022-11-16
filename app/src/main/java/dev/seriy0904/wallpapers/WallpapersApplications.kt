package dev.seriy0904.wallpapers

import android.app.Application
import dev.seriy0904.wallpapers.data.api.WallhavenApi
import dev.seriy0904.wallpapers.data.api.WALLHAVEN_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WallpapersApplications : Application() {

    private lateinit var wallhavenApi: WallhavenApi
    private lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        retrofit = Retrofit.Builder()
            .baseUrl(WALLHAVEN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        wallhavenApi =
            retrofit.create(WallhavenApi::class.java)
    }

    fun getApi(): WallhavenApi {
        return wallhavenApi
    }
}
package com.systems.unsplashunboxed.data.interfaces

import com.systems.unsplashunboxed.data.models.ApiResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api interface
 *
 * @constructor Create empty Api interface
 */
interface ApiInterface {
    /**
     * Get unsplash images
     *
     * @param clientId
     * @param perPage
     * @return
     */
    @GET("/photos")
    suspend fun getUnsplashImages(
        @Query("client_id") clientId: String,
        @Query("per_page") perPage: Int
    ): Response<List<ApiResponseData>>
}
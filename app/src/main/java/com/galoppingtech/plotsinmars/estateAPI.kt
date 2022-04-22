package com.galoppingtech.plotsinmars

import retrofit2.Response
import retrofit2.http.GET

interface estateAPI {
    @GET("/realestate")
    suspend fun getEstates():Response<List<Model>>
}
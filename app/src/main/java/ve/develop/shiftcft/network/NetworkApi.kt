package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET
import retrofit2.http.Path
import ve.develop.shiftcft.model.CardDetail

interface NetworkApi {

    @GET("{bin}")
    suspend fun fetchCardDetail(@Path("bin") id: String): CardDetail

}

package com.example.moviev2.data.service

import com.example.moviev2.data.model.now.Now
import com.example.moviev2.data.model.popular.Popular
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("now_playing")
    fun getNow(
        @Query("api_key") api_key: String,
        @Query("language") language:String

    ): Observable<Now>

}
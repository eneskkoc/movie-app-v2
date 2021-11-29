package com.example.moviev2.data

import com.example.moviev2.data.model.now.Now
import com.example.moviev2.data.model.popular.Popular
import com.example.moviev2.data.service.Api
import com.example.moviev2.data.service.PopularApi
import com.example.moviev2.di.PerApplication
import com.example.moviev2.util.Constants
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class AppDataManager @Inject constructor(private val mApi: Api,private val mPopular:PopularApi) {

    fun api() : Observable<Now> {
        return mApi.getNow(Constants.API_KEY,Constants.Languae)
    }

    fun popularApi() : Observable<Popular> {
        return mPopular.getPopular(Constants.API_KEY,Constants.Languae)
    }

}
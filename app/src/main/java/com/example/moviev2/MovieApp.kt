package com.example.moviev2

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.moviev2.di.DaggerAppComponent

class MovieApp: DaggerApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun applicationInjector(): AndroidInjector<out MovieApp> {
        return DaggerAppComponent.builder().app(this).build()
    }
}

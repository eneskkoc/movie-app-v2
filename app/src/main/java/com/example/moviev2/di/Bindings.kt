package com.example.moviev2.di

import com.example.moviev2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {
    @PerActivity
    @ContributesAndroidInjector(modules =[MainActivityProvides::class])
    abstract fun bindMainActivity(): MainActivity
}
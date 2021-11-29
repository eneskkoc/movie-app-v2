package com.example.moviev2.di

import com.example.moviev2.ui.fragment.DetailFragment
import com.example.moviev2.ui.fragment.MainFragment
import com.example.moviev2.ui.fragment.PopularFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvides {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): MainFragment
    @ContributesAndroidInjector
    abstract fun bindDetailFragment():DetailFragment
    @ContributesAndroidInjector
    abstract fun bindPopularFragment():PopularFragment
}
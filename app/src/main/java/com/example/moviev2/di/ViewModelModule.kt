package com.example.moviev2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviev2.ui.fragment.DetailViewModel
import com.example.moviev2.ui.fragment.MainFragmentViewModel
import com.example.moviev2.ui.fragment.PopularViewModel
import com.example.moviev2.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @PerApplication
    abstract fun bindViewModelFactory(factory:ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: MainFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindPopularViewModel(viewModel: PopularViewModel) : ViewModel

}
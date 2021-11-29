package com.example.moviev2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviev2.R
import com.example.moviev2.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewmodel: MainViewModel
    private var binding: ActivityMainBinding?=null
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        viewmodel = ViewModelProvider(this,viewModelProvider).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }
}
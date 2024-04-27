package com.systems.unsplashunboxed.data.di

import com.systems.unsplashunboxed.data.interfaces.ApiInterface
import com.systems.unsplashunboxed.data.retrofit.RetrofitService
import com.systems.unsplashunboxed.ui.home.HomeActivityViewModel
import dagger.Component

@Component
interface DaggerHelper {
//required dependency will be defined here

    fun dependencyProvider(homeActivityViewModel: HomeActivityViewModel)
}
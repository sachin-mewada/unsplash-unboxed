package com.systems.unsplashunboxed.data.di

import com.systems.unsplashunboxed.data.interfaces.ApiInterface
import com.systems.unsplashunboxed.data.retrofit.RetrofitService
import dagger.Component

@Component
interface DaggerHelper {

    fun retrofitService():RetrofitService
}
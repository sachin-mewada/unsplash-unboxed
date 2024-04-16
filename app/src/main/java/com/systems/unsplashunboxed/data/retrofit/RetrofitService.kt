package com.systems.unsplashunboxed.data.retrofit

import android.content.Context
import com.systems.unsplashunboxed.data.interfaces.ApiInterface
import com.systems.unsplashunboxed.utils.Constants
import com.systems.unsplashunboxed.utils.Utils.isInternetAvailable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Get retrofit service
 *
 * @param context
 * @return
 */
fun getRetrofitService(context: Context): ApiInterface {

    val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .client(provideHttpClient(context))
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(ApiInterface::class.java)
}

/**
 * Provide logging interceptor
 *
 * @return
 */
fun provideLoggingInterceptor(): Interceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
}

/**
 * Provide http client
 *
 * @param context
 * @return
 */
fun provideHttpClient(context: Context): OkHttpClient {

    val onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    val offlineInterceptor = Interceptor { chain ->
        var request: Request = chain.request()
        if (!isInternetAvailable(context)) {
            val maxStale = 60 * 60 * 24 * 1
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

    val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
    val cache = Cache(context.cacheDir, cacheSize)


    return OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .addInterceptor(offlineInterceptor)
        .addNetworkInterceptor(onlineInterceptor)
        .cache(cache)
        .build()
}


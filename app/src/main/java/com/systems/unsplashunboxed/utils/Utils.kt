package com.systems.unsplashunboxed.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.systems.unsplashunboxed.R
import com.systems.unsplashunboxed.data.models.ApiError
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_NOT_IMPLEMENTED
import java.net.HttpURLConnection.HTTP_SERVER_ERROR
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.net.HttpURLConnection.HTTP_UNAVAILABLE
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Utils {
    /**
     * Is internet available
     *
     * @param context
     * @return
     */
    fun isInternetAvailable(context: Context): Boolean {
        var isConnected: Boolean = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    /**
     * Api error handler
     *
     * @param error
     * @param exception
     * @return
     */
    fun apiErrorHandler(error: okhttp3.Response?, exception: Exception?): ApiError {
        return if (error != null) {
            when (error.code) {
                HTTP_BAD_REQUEST -> {
                    ApiError(
                        R.drawable.id_error,
                        "The request was unacceptable, often due to missing a required parameter"
                    )
                }

                HTTP_UNAUTHORIZED -> {
                    ApiError(
                        R.drawable.id_error,
                        "Unauthorized Access"
                    )
                }

                HTTP_FORBIDDEN -> {
                    ApiError(
                        R.drawable.id_error,
                        "Missing permissions to perform request"
                    )
                }

                HTTP_NOT_FOUND -> {
                    ApiError(
                        R.drawable.id_error,
                        "The requested resource doesn’t exist"
                    )
                }

                HTTP_SERVER_ERROR,
                HTTP_NOT_IMPLEMENTED,
                HTTP_UNAVAILABLE -> {
                    ApiError(R.drawable.id_error, "Something went wrong on server: ${error.code}")
                }

                else -> {
                    ApiError(R.drawable.id_error, "${error.message}: ${error.code}")
                }
            }
        } else {
            when (exception) {
                is SocketTimeoutException -> {
                    ApiError(R.drawable.id_error, "Connection timed out. Please try again.")
                }

                is UnknownHostException -> {
                    ApiError(
                        R.drawable.id_error,
                        "Unable to connect to the server. Please check your internet connection."
                    )
                }

                is ConnectException -> {
                    ApiError(
                        R.drawable.id_error,
                        "Failed to establish connection. Please check your network settings."
                    )
                }

                is MalformedURLException -> {
                    ApiError(
                        R.drawable.id_error,
                        "Invalid URL. Please check the request URL and try again."
                    )
                }

                is IllegalStateException -> {
                    ApiError(
                        R.drawable.id_error,
                        "Unexpected error. Please try again later or contact support."
                    )
                }

                is IOException -> {
                    ApiError(
                        R.drawable.id_error,
                        "Network error. Please check your internet connection and try again."
                    )
                }

                else -> {
                    ApiError(R.drawable.id_error, "Unexpected error: ${exception?.message}")
                }
            }
        }
    }
}
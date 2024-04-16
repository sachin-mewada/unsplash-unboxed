package com.systems.unsplashunboxed.ui.home

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.systems.unsplashunboxed.data.models.ApiError
import com.systems.unsplashunboxed.data.models.ApiResponseData
import com.systems.unsplashunboxed.data.retrofit.getRetrofitService
import com.systems.unsplashunboxed.utils.Constants
import com.systems.unsplashunboxed.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Home activity view model
 *
 * @constructor Create empty Home activity view model
 */
class HomeActivityViewModel() : ViewModel() {

    private val _liveData = MutableLiveData<List<ApiResponseData>>()
    val liveData: LiveData<List<ApiResponseData>> = _liveData



    private val _errorLiveData = MutableLiveData<ApiError>()
    val errorLiveData: LiveData<ApiError> = _errorLiveData


    /**
     * Get images
     *
     * @param context
     */
    fun getImages(context: Context) {
        viewModelScope.launch {
            try {
                val response =
                    getRetrofitService(context).getUnsplashImages(Constants.CLIENT_ID, 100)
                if (response.isSuccessful) {
                    response.body()?.let {
                    _liveData.postValue(it)
                    }?:_errorLiveData.postValue(Utils.apiErrorHandler(response.raw(), null))
                } else {
                    _errorLiveData.postValue(Utils.apiErrorHandler(response.raw(), null))
                }
            } catch (ex: Exception) {
                Log.e("error123", ex.message!!)
                _errorLiveData.postValue(Utils.apiErrorHandler(null, ex))
            }
        }
    }
}
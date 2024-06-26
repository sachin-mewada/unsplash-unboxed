package com.systems.unsplashunboxed.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.systems.unsplashunboxed.data.models.ApiCallingState
import com.systems.unsplashunboxed.data.retrofit.getRetrofitService
import com.systems.unsplashunboxed.utils.Constants
import com.systems.unsplashunboxed.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Home activity view model
 *
 * @constructor Create empty Home activity view model
 */
class HomeActivityViewModel() : ViewModel() {

    /**
     * _image state
     */
    private val _imageState = MutableLiveData<ApiCallingState>()

    /**
     * Image state
     */
    val imageState: LiveData<ApiCallingState> = _imageState

    /**
     * Get images
     *
     * @param context
     */

    fun getImages(context: Context) {
        _imageState.value = ApiCallingState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    getRetrofitService(context).getUnsplashImages(Constants.CLIENT_ID, 100)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _imageState.postValue(ApiCallingState.Success(it))
                    } ?: _imageState.postValue(
                        ApiCallingState.Error(
                            Utils.apiErrorHandler(
                                response.raw(),
                                null
                            )
                        )
                    )
                } else {
                    _imageState.postValue(
                        ApiCallingState.Error(
                            Utils.apiErrorHandler(
                                response.raw(),
                                null
                            )
                        )
                    )
                }
            } catch (ex: Exception) {
                Log.e("error123", ex.message!!)
                _imageState.postValue(ApiCallingState.Error(Utils.apiErrorHandler(null, ex)))
            }
        }
    }
}
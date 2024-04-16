package com.systems.unsplashunboxed.data.models

/**
 * Api calling state
 *
 * @constructor Create empty Api calling state
 */
sealed class ApiCallingState {
    object Loading : ApiCallingState()
    data class Success(val data: List<ApiResponseData>) : ApiCallingState()
    data class Error(val error: ApiError) : ApiCallingState()
}

package com.systems.unsplashunboxed.data.models

/**
 * Api error
 *
 * @property image
 * @property message
 * @constructor Create empty Api error
 */
data class ApiError(
    val image: Int,
    val message: String
)
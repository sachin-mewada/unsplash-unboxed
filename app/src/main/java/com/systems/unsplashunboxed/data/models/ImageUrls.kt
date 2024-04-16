package com.systems.unsplashunboxed.data.models

/**
 * Image urls
 *
 * @property full
 * @property raw
 * @property regular
 * @property small
 * @property thumb
 * @constructor Create empty Image urls
 */
data class ImageUrls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)

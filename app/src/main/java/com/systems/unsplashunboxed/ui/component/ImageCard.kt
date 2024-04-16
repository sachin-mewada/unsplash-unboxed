package com.systems.unsplashunboxed.ui.component

import android.util.Log
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.systems.unsplashunboxed.R

/**
 * Image card
 *
 * @param url
 */
@Composable
fun ImageCard(url: String) {
    Card {
        Log.d("imageCard", url)
        AsyncImage(
            placeholder = painterResource(id = R.drawable.spinner_1x_1_0s_200px_200px__1_),
            model = url,
            contentDescription = null,
            error = painterResource(id = R.drawable.id_error)
        )
    }
}
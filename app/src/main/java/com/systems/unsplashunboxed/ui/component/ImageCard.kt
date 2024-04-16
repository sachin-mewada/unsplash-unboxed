package com.systems.unsplashunboxed.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import com.systems.unsplashunboxed.R

/**
 * Image card
 *
 * @param url
 */
@Composable
fun ImageCard(url: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            placeholder(R.drawable.transparent_bckground)
        }
    )

    Card {
        Box {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                    )
                }

                is AsyncImagePainter.State.Error -> {
                    Image(
                        painter = painterResource(id = R.drawable.id_error),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center)
                    )
                }

                else -> {
                    //success case
                }
            }
        }
    }
}

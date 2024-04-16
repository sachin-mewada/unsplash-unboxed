package com.systems.unsplashunboxed.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.systems.unsplashunboxed.R
import com.systems.unsplashunboxed.ui.component.ErrorView
import com.systems.unsplashunboxed.ui.component.ImageGridView

/**
 * Home activity
 *
 * @constructor Create empty Home activity
 */
class HomeActivity : ComponentActivity() {
    private val viewModel : HomeActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getImages(this)
        viewModel.liveData.observe(this){
            setContent {
                    ImageGridView(list =it)
            }
        }
        viewModel.errorLiveData.observe(this){
            setContent {
                ErrorView(it.image, it.message)
            }
        }
    }
}




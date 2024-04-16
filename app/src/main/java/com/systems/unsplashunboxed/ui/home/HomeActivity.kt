package com.systems.unsplashunboxed.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.systems.unsplashunboxed.data.models.ApiCallingState
import com.systems.unsplashunboxed.ui.component.ErrorView
import com.systems.unsplashunboxed.ui.component.ImageGridView
import com.systems.unsplashunboxed.ui.component.LoadingView

/**
 * Home activity
 *
 * @constructor Create empty Home activity
 */
class HomeActivity : ComponentActivity() {
    private val viewModel: HomeActivityViewModel by viewModels()

    /**
     * On create
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.imageState.observe(this) {
            when (it) {
                is ApiCallingState.Loading -> {
                    setContent {
                        LoadingView()
                    }
                }

                is ApiCallingState.Success -> {
                    setContent {
                        ImageGridView(list = it.data)
                    }
                }

                is ApiCallingState.Error -> {
                    setContent {
                        ErrorView(it.error.image, it.error.message)
                    }
                }
            }
        }
    }

    /**
     * On resume
     *
     */
    override fun onResume() {
        viewModel.getImages(this@HomeActivity)
        super.onResume()
    }
}




package com.systems.unsplashunboxed.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.systems.unsplashunboxed.ui.component.ErrorView
import com.systems.unsplashunboxed.ui.component.ImageGridView

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
        viewModel.liveData.observe(this) {
            setContent {
                ImageGridView(list = it)
            }
        }
        viewModel.errorLiveData.observe(this) {
            setContent {
                ErrorView(it.image, it.message)
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




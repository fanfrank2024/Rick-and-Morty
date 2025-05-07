package com.example.huma.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.huma.compose.Dashboard
import com.example.huma.compose.LoadingSpinner
import com.example.huma.compose.MainAppBar
import com.example.huma.ui.theme.HumaTheme
import com.example.huma.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HumaTheme {
                val figures by viewModel.figures.collectAsState(initial = emptyList())
                Dashboard(
                    modifier = Modifier.fillMaxSize(),
                    figures
                )
            }
        }
    }
}
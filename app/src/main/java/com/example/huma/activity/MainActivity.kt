package com.example.huma.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.huma.compose.Dashboard
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

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") {
                        val figures by viewModel.filteredFigures.collectAsState(initial = emptyList())
                        val searchQuery by viewModel.searchQuery.collectAsState()

                        Dashboard(
                            modifier = Modifier.fillMaxSize(),
                            figures,
                            searchQuery,
                            onSearchQueryChange = { query ->
                                viewModel.searchQuery.value = query
                            },
                            onFigureClick = { figure ->
                                navController.navigate("detail/${figure.id}")
                            }
                        )
                    }
                    composable("detail/{figureId}") { navBackStackEntry ->
                        val figureId = navBackStackEntry.arguments?.getString("figureId")
                        //DetailScreen(figureId = figureId)
                    }
                }

            }
        }
    }
}
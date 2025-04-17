package com.example.huma.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.huma.activity.ui.theme.HumaTheme
import com.example.huma.data.Figure

@Composable
fun Dashboard(
    modifier: Modifier,
    figure: List<Figure>
) {
    DisplayFigures(modifier, figure)
}

@Composable
fun DisplayFigures(
    modifier: Modifier,
    figure: List<Figure>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
    ) {
        items(figure.size) { index ->
            val figure = figure[index]
            Column {

                Text(
                    text = figure.name
                )
                Text(
                    text = figure.status
                )
            }
        }
    }
}

@Preview
@Composable
fun DashboardPreview() {
    HumaTheme {
        Dashboard(modifier = Modifier, figure = listOf(Figure(1, "Rick","image1", "alive"),
            Figure(2, "Morty", "image2", "alive"), Figure(3, "Summer", "image3", "alive")))
    }
}
package com.example.huma.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            Column (
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = figure.image,
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxWidth()
                )
                Text(text = figure.name)
                Text(text = figure.status)
            }
        }
    }
}



@Preview
@Composable
fun DashboardPreview() {
    HumaTheme {
        Dashboard(modifier = Modifier.fillMaxSize(), figure = listOf())
    }
}
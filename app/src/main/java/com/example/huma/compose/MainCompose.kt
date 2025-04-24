package com.example.huma.compose

import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.huma.R
import com.example.huma.activity.ui.theme.HumaTheme
import com.example.huma.data.Figure
import com.example.huma.data.Location
import com.example.huma.data.Origin

@Composable
fun Dashboard(
    modifier: Modifier,
    figures: List<Figure>
) {
    val isLoading = figures.isEmpty()
    Box(modifier) {

        Image(
            painter = painterResource(R.drawable.splash_background),
            contentDescription = "Rick and Morty background",
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier,
                text = "All Characters",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            )
            if (isLoading) {
                LoadingSpinner(modifier)
            } else {
                DisplayFigures(
                    modifier,
                    figures
                )
            }
        }
    }
}

@Composable
fun DisplayFigures(
    modifier: Modifier,
    figures: List<Figure>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
    ) {
        items(figures.size) { index ->
            val figure = figures[index]
            FigureCard(modifier, figure)
        }
    }
}

@Composable
fun FigureCard(
    modifier: Modifier,
    figure: Figure
) {
    var containerColor = Color.White
    var textColor = Color.Black
    if(figure.status == "Dead") {
        containerColor = Color.Red
        textColor = Color.White
    }
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = figure.image,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                color = textColor,
                fontWeight = FontWeight.Bold,
                text = figure.name
            )
            Text(
                color = textColor,
                text = figure.status
            )
        }
    }
}

@Composable
fun ThemedLoadingSpinner(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = R.drawable.loading_spinner,
            contentDescription = "Loading",
            modifier = modifier.size(80.dp)
        )
        CircularProgressIndicator(
            color = Color(0xFF53B649),
            strokeWidth = 4.dp,
            modifier = modifier.size(40.dp)
        )
    }
}

@Composable
fun LoadingSpinner(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.White
        )
    }
}

@Preview
@Composable
fun DashboardPreview() {
    HumaTheme {
        Dashboard(modifier = Modifier.fillMaxSize(), listOf())
    }
}
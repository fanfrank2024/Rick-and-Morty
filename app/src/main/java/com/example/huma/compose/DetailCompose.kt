package com.example.huma.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.huma.data.Figure
import com.example.huma.data.Location
import com.example.huma.data.Origin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FigureDetail(
    figureId: String,
    figures: List<Figure>,
    onBack: () -> Unit
) {
    val figure = figures.find { figureId == it.id.toString() }

    if (figure != null) {
        Scaffold(
            contentColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            figure.name,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1B342C)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.Rounded.ArrowBackIosNew,
                                "Back",
                                tint = Color(0xFF1B342C)
                            )
                        }
                    },
                    colors = TopAppBarColors(
                        containerColor = Color(0xFFE8F5E9),
                        scrolledContainerColor = Color(0xFFE8F5E9),
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                FigureHeaderImage(figure.image)
                FigureInfoSection(figure)
            }
        }
    } else {
        Text(
            text = "Figure Not Found",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Red,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun FigureHeaderImage(
    figureImage: String
) {
    AsyncImage(
        model = figureImage,
        contentDescription = "Detail Screen Figure Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun FigureInfoSection(figure: Figure) {
    Column(
        modifier = Modifier
            .background(Color(0xFFE8F5E9))
            .fillMaxSize(),
    ) {
        InfoRow("Status:", figure.status)
        InfoRow("Species:", figure.species)
        InfoRow("Gender:", figure.gender)
        InfoRow("Origin:", figure.origin.name)
        InfoRow("Location:", figure.location.name)
        InfoRow("Created:", figure.created)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row (
        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun DetailPreview() {

    val figure = Figure(
        1,
        "Morty",
        "Alive",
        "Human",
        "type",
        "Male",
        Origin("Earth", "url"),
        Location("US", "url"),
        "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
        listOf(),
        "url",
        "created"
    )

    FigureDetail(
        "1", listOf(figure), {}
    )
}
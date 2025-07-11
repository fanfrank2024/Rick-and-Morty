package com.example.huma.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.huma.R
import com.example.huma.activity.ui.theme.HumaTheme
import com.example.huma.data.Figure


@Composable
fun Dashboard(
    modifier: Modifier,
    figures: List<Figure>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onFigureClick: (Figure) -> Unit
) {

    val isLoading = figures.isEmpty() && searchQuery.isBlank()
    val noResult = figures.isEmpty() && searchQuery.isNotBlank()

    Scaffold(
        topBar = { MainAppBar(searchQuery, onSearchQueryChange) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .background(Color(0xFF2C4D42))
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                isLoading -> LoadingSpinner(modifier)
                noResult -> NoResultFound(modifier)
                else -> DisplayFigures(modifier, figures, onFigureClick)
            }
            if (isLoading) {
                LoadingSpinner(modifier)
            } else {
                DisplayFigures(
                    modifier,
                    figures,
                    onFigureClick
                )
            }
        }
    }
}

@Composable
fun NoResultFound(modifier: Modifier) {
    Text(
        textAlign = TextAlign.Center,
        text = "Result is not found!",
        color = Color.White,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    val isSearchBarVisible = remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isSearchBarVisible.value) {
                SearchBar(searchQuery, onSearchQueryChange)
            } else {
                Text(
                    text = "All Characters",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B342C)
                )
            }
        },
        actions = {
            SearchAndExit(
                isSearchBarVisible,
                onClearSearch = { onSearchQueryChange("") }
            )
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

@Composable
fun DisplayFigures(
    modifier: Modifier,
    figures: List<Figure>,
    onFigureClick: (Figure) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
    ) {
        items(figures.size) { index ->
            val figure = figures[index]
            FigureCard(modifier, figure, onFigureClick)
        }
    }
}

@Composable
fun FigureCard(
    modifier: Modifier,
    figure: Figure,
    onFigureClick: (Figure) -> Unit
) {

    val containerColor = when (figure.status) {
        "Alive" -> Color(0xFF53B649)
        "Dead" -> Color(0xFFE53935)
        else -> Color(0xFFFDD835)
    }

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = { onFigureClick(figure) }
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = figure.image,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(2.dp, Color.White, RoundedCornerShape(12.dp)),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                text = figure.name
            )
            Text(
                color = Color.White,
                text = figure.status,
                style = MaterialTheme.typography.bodyMedium
            )
        }
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

@Composable
fun SearchAndExit(isSearchBarVisible: MutableState<Boolean>, onClearSearch: () -> Unit) {
    if(isSearchBarVisible.value) ExitIcon(isSearchBarVisible, onClearSearch)
    else SearchIcon(isSearchBarVisible)
}

@Composable
fun SearchIcon(isSearchBarVisible: MutableState<Boolean>) {
    IconButton(
        onClick = {
            isSearchBarVisible.value = !isSearchBarVisible.value
        },
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color(0xFF1B342C)
        )
    }
}

@Composable
fun ExitIcon(isSearchBarVisible: MutableState<Boolean>, onClearSearch: () -> Unit) {
    IconButton(
        onClick = {
            isSearchBarVisible.value = !isSearchBarVisible.value
            onClearSearch()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Search",
            tint = Color(0xFF1B342C)
        )
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchQueryChange: (String) -> Unit) {

    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = { Text("Search...") },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color(0xFF1B342C)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFF1B342C), RoundedCornerShape(24.dp))
            .padding(horizontal = 8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun DashboardPreview() {
    HumaTheme {
        Dashboard(modifier = Modifier.fillMaxSize(), listOf(), "Morty", {}, {})
    }
}
package com.example.huma.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.huma.R
import com.example.huma.ui.theme.HumaTheme

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.splash_background),
            contentDescription = "Rick and Morty splash",
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(
                text = "RICK AND MORTY",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            )

            Text(
                text = "All you need to know about the show",
                modifier = modifier,
                fontSize = 15.sp,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HumaTheme {
        Greeting()
    }
}


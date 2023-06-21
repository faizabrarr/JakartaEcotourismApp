package com.example.jakartaecotourismapp.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jakartaecotourismapp.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate("home")
    }

    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha : Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.White else Color.White)
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .alpha(alpha = alpha)
                .align(alignment = Alignment.Center),
            painter = painterResource(R.drawable.jakartaecotourism1),
            contentDescription = "Logo"
        )
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .alpha(alpha = alpha)
                .padding(top = 225.dp),
            text = "Muhammad Faiz Abrar Fatah",
            color = Color.Black,

        )
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .alpha(alpha = alpha)
                .padding(top = 275.dp),
            text = "50420818",
            color = Color.Black,
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}
package com.example.jakartaecotourismapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jakartaecotourismapp.ui.features.DetailScreen1
import com.example.jakartaecotourismapp.ui.features.DetailScreen10
import com.example.jakartaecotourismapp.ui.features.DetailScreen2
import com.example.jakartaecotourismapp.ui.features.DetailScreen3
import com.example.jakartaecotourismapp.ui.features.DetailScreen4
import com.example.jakartaecotourismapp.ui.features.DetailScreen5
import com.example.jakartaecotourismapp.ui.features.DetailScreen6
import com.example.jakartaecotourismapp.ui.features.DetailScreen7
import com.example.jakartaecotourismapp.ui.features.DetailScreen8
import com.example.jakartaecotourismapp.ui.features.DetailScreen9
import com.example.jakartaecotourismapp.ui.features.HomeScreen
import com.example.jakartaecotourismapp.ui.theme.JakartaEcotourismAppTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JakartaEcotourismAppTheme {
                val navController = rememberNavController()

                ProvideWindowInsets() {

                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {

                            composable("home") {
                                HomeScreen(navController)
                            }

                            composable("detail1") {
                                DetailScreen1(navController, activityResultRegistry)
                            }

                            composable("detail2") {
                                DetailScreen2(navController, activityResultRegistry)
                            }

                             composable("detail3") {
                                DetailScreen3(navController, activityResultRegistry)
                             }

                            composable("detail4") {
                                DetailScreen4(navController, activityResultRegistry)
                            }

                            composable("detail5") {
                                DetailScreen5(navController, activityResultRegistry)
                            }

                            composable("detail6") {
                                DetailScreen6(navController, activityResultRegistry)
                            }

                            composable("detail7") {
                                DetailScreen7(navController, activityResultRegistry)
                            }

                            composable("detail8") {
                                DetailScreen8(navController, activityResultRegistry)
                            }

                            composable("detail9") {
                                DetailScreen9(navController, activityResultRegistry)
                            }

                            composable("detail10") {
                                DetailScreen10(navController, activityResultRegistry)
                            }

                        }

                    }

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JakartaEcotourismAppTheme {
        Greeting("Android")
    }
}
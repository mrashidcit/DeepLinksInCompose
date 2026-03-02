package com.rashid.saleem.deeplinksincompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.rashid.saleem.deeplinksincompose.ui.theme.DeeplinksInComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeeplinksInComposeTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.Home
                ) {

                    composable<Routes.Home> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate(Routes.Detail())
                                }
                            ) {
                                Text("To detail")
                            }
                        }
                    }

                    composable<Routes.Detail>(
                        deepLinks = listOf(
                            navDeepLink {
                                uriPattern = "https://pl-coding.com/{id}"
                                action = Intent.ACTION_VIEW
                            }
                        ),
                    ) { backStackEntry ->
                        val id = remember(backStackEntry) {
                            backStackEntry.toRoute<Routes.Detail>().id
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("The id is $id")
                        }
                    }
                }
            }
        }
    }
}
















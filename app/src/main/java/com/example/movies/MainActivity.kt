package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.screens.DetailsScreen
import com.example.movies.screens.MainScreen
import com.example.movies.screens.TrailerScreen
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var videoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoPlayer = ExoPlayer.Builder(this).build()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.init()

        setContent {
            MoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val windowInfo = rememberWindowInfo()

                    NavHost(
                        navController = navController,
                        startDestination = "MainScreen"
                    ) {
                        composable(route = "MainScreen") {
                            MainScreen(moviesImgTitleList = mainViewModel.getMoviesImagesTitles(), navController)
                        }
                        composable(route = "TrailerScreen") {
                            TrailerScreen(navController, windowInfo, mainViewModel.getMoviesImagesTitles())
                        }
                        composable(route = "DetailsScreen/{index}",
                            arguments = listOf(
                                navArgument(name = "index"){
                                    type = NavType.IntType
                                }
                            )
                        ){
                                index ->
                            var itemIndex = index.arguments?.getInt("index")
                            if (itemIndex == null){
                                itemIndex = 0
                            }
                            DetailsScreen(
                                movieDetails = mainViewModel.getMovieDetailsObject(itemIndex),
                                navController = navController,
                                windowInfo = windowInfo
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesListPreview() {
    val imagesList = ContentManager.getDatabaseData().map { it.mainImage }
    val titlesList = ContentManager.getDatabaseData().map { it.title }
    MainScreen(moviesImgTitleList = imagesList.zip(titlesList), navController = rememberNavController())
}
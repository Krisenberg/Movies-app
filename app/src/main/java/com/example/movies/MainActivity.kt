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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.screens.DetailsScreen
import com.example.movies.screens.MainScreen
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.init()

        setContent {
            MoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "MainScreen") {
                            composable(route = "MainScreen") {
                                MainScreen(moviesImgTitleList = mainViewModel.getItemsZippedList(), navController)
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
                                    navController = navController
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
//    MoviesTheme {
////        Greeting("Android")
//        MoviesList(MainViewModel().GetMoviesList())
//    }
    MainScreen(moviesImgTitleList = ContentManager.getMainImagesList().zip(ContentManager.getTitlesList()), navController = rememberNavController())
}
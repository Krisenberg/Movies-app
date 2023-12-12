package com.example.movies

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.OrientationEventListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var videoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesTheme {
                mainViewModel = hiltViewModel<MainViewModel>()

                val isOrientLandscape = requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                mainViewModel.init(isOrientLandscape)

//                mainViewModel.requestedOrientLandscape().observe(this) {
//                    requestedOrientation = if (it)
//                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//                    else
//                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//                }

                videoPlayer = ExoPlayer.Builder(this).build()

//                val orientationEventListener = object : OrientationEventListener(this) {
//                    override fun onOrientationChanged(orientation: Int) {
//                        // The orientation value ranges from 0 to 359 degrees.
//                        // You can check for specific ranges to determine portrait or landscape.
//                        if (orientation in 45..134 || orientation in 225..314) {
//                            mainViewModel.setOrientLand()
//                        } else {
//                            mainViewModel.setOrientPortrait()
//                        }
//                    }
//                }
//
//                orientationEventListener.enable()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val windowInfo = rememberWindowInfo()

//                    val configuration = LocalConfiguration.current
//
//                    // When orientation is Landscape
//                    when (configuration.orientation) {
//                        Configuration.ORIENTATION_LANDSCAPE -> {
//                            mainViewModel.setOrientLand()
//                        }
//                        // Other wise
//                        else -> {
//                            mainViewModel.setOrientPortrait()
//                        }
//                    }

                    NavHost(
                        navController = navController,
                        startDestination = "MainScreen"
                    ) {
                        composable(route = "MainScreen") {navBackStackEntry ->
                            MainScreen(navController, mainViewModel)
                        }
                        composable(route = "TrailerScreen") {
                            TrailerScreen(navController, mainViewModel)
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

//    override fun onResume() {
//        super.onResume()
//
//        val isOrientLandscape = requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//
//        mainViewModel.init(isOrientLandscape)
//
//        mainViewModel.requestedOrientLandscape().observe(this) {
//            requestedOrientation = if (it)
//                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//            else
//                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        }
//    }
    private fun changeOrientation() {
        requestedOrientation = if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MoviesListPreview() {
//    val imagesList = ContentManager.getDatabaseData().map { it.mainImage }
//    val titlesList = ContentManager.getDatabaseData().map { it.title }
//    MainScreen(moviesImgTitleList = imagesList.zip(titlesList), ::MainActivity.changeOrientation, navController = rememberNavController())
//}
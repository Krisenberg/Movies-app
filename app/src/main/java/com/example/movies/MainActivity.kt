package com.example.movies

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.OrientationEventListener
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
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
import com.example.movies.screens.FullscreenTrailerScreen
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

                mainViewModel.init(
                    isOrientLandscape,
                    { requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE },
                    { requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT }
                )

                val backAction by mainViewModel.backAction.observeAsState()

                if (backAction != null) {
                    BackHandler(enabled = true, onBack = backAction!!)
                }

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
                        composable(route = "FullscreenTrailerScreen/{index}",
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
                            FullscreenTrailerScreen(
                                navController = navController,
                                mainViewModel = mainViewModel,
                                trailerID = itemIndex!!
                            )
                        }
                    }
                }
            }
        }
    }

@Composable
fun BackHandler(enabled: Boolean = true, onBack: () -> Unit) {
    // Safely update the current `onBack` lambda when a new one is provided
    val currentOnBack by rememberUpdatedState(onBack)
    // Remember in Composition a back callback that calls the `onBack` lambda
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                currentOnBack()
            }
        }
    }
    // On every successful composition, update the callback with the `enabled` value
    SideEffect {
        backCallback.isEnabled = enabled
    }
    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher) {
        // Add callback to the backDispatcher
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        // When the effect leaves the Composition, remove the callback
        onDispose {
            backCallback.remove()
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
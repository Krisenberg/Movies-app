package com.example.movies.screens

import android.content.res.Configuration
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movies.MainViewModel

@androidx.annotation.OptIn(UnstableApi::class) @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullscreenTrailerScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    trailerID: Int,
    modifier: Modifier = Modifier
){
//    mainViewModel.init2()
    Log.d("MyInfo", "FULLSCREEN")

//    val player = mainViewModel.getPlayer(trailerID)
//    val context = LocalContext.current
//
//    val playerView = PlayerView(context)
//    playerView.player = player
//    playerView.useController = true
//    playerView.keepScreenOn = true
    val videoItem = mainViewModel.mediaItems.collectAsState()

//    val playerView = mainViewModel.getPlayerView(trailerID)
//
//    when (LocalConfiguration.current.orientation) {
//        Configuration.ORIENTATION_LANDSCAPE -> {
//            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//        }
//        // Other wise
//        else -> {
//            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
//        }
//    }
//

    val orient = LocalConfiguration.current.orientation
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            mainViewModel.player.pause()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

//    fun getPlayerView(trailerID: Int): PlayerView {
//        player!!.prepare()
//
//        val playerView = PlayerView(application.applicationContext)
//        playerView.player = player!!
//
////        val playerView = PlayerView(context)
////        playerView.player = player
//        playerView.useController = true
//        playerView.keepScreenOn = true
//        player!!.seekTo(trailerID,0)
//        return playerView
//    }
//
//    val playerView = mainViewModel.getPlayerView(trailerID)
//    playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
//    mainViewModel.stopPlayerView()
//    playerView.player!!.prepare()

    AndroidView(
        factory = { context ->
            PlayerView(context).also {
                it.player = mainViewModel.player
                (it.player as ExoPlayer).seekTo(trailerID,0)
                when (orient) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    }
                    else -> {
                        it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                    }
                }
            }
        },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                    }
                    else -> Unit
                }
            },
        modifier = Modifier
            .fillMaxSize()
//                .rotate(90f)
//                .fillMaxSize()
//                .aspectRatio(16 / 9f)
    )
//    Column(
//        modifier =
//            modifier
//                .fillMaxSize(1f),
//        verticalArrangement = Arrangement.spacedBy((-8).dp)
//    ){
//        val movies = mainViewModel.getMovieTrailers()
//
//        val context = LocalContext.current
//        val exoPlayer = ExoPlayer.Builder(context).build()
//        for (trailerUri in movies) {
//            val mediaItem = MediaItem.fromUri(trailerUri)
//            exoPlayer.addMediaItem(mediaItem)
//        }
//        exoPlayer.prepare()
//
//        val playerView = PlayerView(context)
//        playerView.player = exoPlayer
//        exoPlayer.seekTo(trailerID,0)

//        val playerView = PlayerView(context)
//        playerView.player = player
//        playerView.useController = true
//        val playerView = mainViewModel.getPlayerView(trailerID)
//        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
//        mainViewModel.stopPlayerView()
//        playerView.keepScreenOn = true

//        var lifecycle by remember {
//            mutableStateOf(Lifecycle.Event.ON_CREATE)
//        }

//        DisposableEffect(Unit) {
//            onDispose {
//                playerView.player!!.pause()
//            }
//        }

//        BackHandler(onBack = {
//            playerView.player!!.pause()
//            navController.navigate(route = "MainScreen")
//        })
//        mainViewModel.performBackAction {
//            // Define the action to be performed on back
//            playerView.player!!.pause()
//            navController.navigate(route = "MainScreen")
//        }

//        IconButton(
//            modifier = Modifier
//                .alpha(0.8f)
//                .weight(1f),
//            onClick = {
//                playerView.player!!.pause()
//                navController.navigate(route = "MainScreen")
//            }) {
//            Icon(
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = "Back arrow",
//                modifier = Modifier.size(48.dp),
//                tint = Color.White
//            )
//        }
//
//        AndroidView(
//            factory = { playerView },
//            update = {
//                when (lifecycle) {
//                    Lifecycle.Event.ON_PAUSE -> {
//                        it.onPause()
//                        it.player?.pause()
//                    }
//                    Lifecycle.Event.ON_RESUME -> {
//                        it.onResume()
//                    }
//                    else -> Unit
//                }
//            },
//            modifier = Modifier
//                .fillMaxSize()
//                .rotate(90f)
//                .fillMaxSize()
//                .aspectRatio(16 / 9f)
//        )
//    }
}

//@Composable
//fun BackHandler(enabled: Boolean = true, onBack: () -> Unit) {
//    // Safely update the current `onBack` lambda when a new one is provided
//    val currentOnBack by rememberUpdatedState(onBack)
//    // Remember in Composition a back callback that calls the `onBack` lambda
//    val backCallback = remember {
//        object : OnBackPressedCallback(enabled) {
//            override fun handleOnBackPressed() {
//                currentOnBack()
//            }
//        }
//    }
//    // On every successful composition, update the callback with the `enabled` value
//    SideEffect {
//        backCallback.isEnabled = enabled
//    }
//    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
//        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
//    }.onBackPressedDispatcher
//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(lifecycleOwner, backDispatcher) {
//        // Add callback to the backDispatcher
//        backDispatcher.addCallback(lifecycleOwner, backCallback)
//        // When the effect leaves the Composition, remove the callback
//        onDispose {
//            backCallback.remove()
//        }
//    }
//}
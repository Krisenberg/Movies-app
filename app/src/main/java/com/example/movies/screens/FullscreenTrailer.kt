package com.example.movies.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
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
    Column(
        modifier =
            modifier
                .fillMaxSize(1f),
        verticalArrangement = Arrangement.spacedBy((-8).dp)
    ){
        val movies = mainViewModel.getMovieTrailers()

        val context = LocalContext.current
        val exoPlayer = ExoPlayer.Builder(context).build()
        for (trailerUri in movies) {
            val mediaItem = MediaItem.fromUri(trailerUri)
            exoPlayer.addMediaItem(mediaItem)
        }
        exoPlayer.prepare()

        val playerView = PlayerView(context)
        playerView.player = exoPlayer

        exoPlayer.seekTo(trailerID,0)

//        val playerView = PlayerView(context)
//        playerView.player = player
        playerView.useController = true
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
        playerView.keepScreenOn = true

        var lifecycle by remember {
            mutableStateOf(Lifecycle.Event.ON_CREATE)
        }

        IconButton(
            modifier = Modifier
                .alpha(0.8f)
                .weight(1f),
            onClick = {
                playerView.player!!.pause()
                navController.navigate(route = "MainScreen")
            }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back arrow",
                modifier = Modifier.size(48.dp),
//                tint = Color.White
            )
        }

        AndroidView(
            factory = { playerView },
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
    }
}
package com.example.movies.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.movies.MainViewModel

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun FullscreenTrailerScreen(
    mainViewModel: MainViewModel,
    trailerID: Int
){

    val orient = LocalConfiguration.current.orientation

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        onDispose {
            mainViewModel.player.pause()
            mainViewModel.setLastPlayedTrailerOrientation(orient)
            mainViewModel.setLastPlayedTrailerPosition(mainViewModel.player.currentPosition)
            mainViewModel.setLastPlayedTrailerID(mainViewModel.player.currentMediaItemIndex)
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).also {
                it.player = mainViewModel.player
                var playerPos: Long = 0
                if (mainViewModel.getLastPlayedTrailerID() != -1 && mainViewModel.getLastPlayedTrailerOrientation() != orient)
                    playerPos = mainViewModel.getLastPlayedTrailerPosition()

                val mediaItemIndex = if (mainViewModel.getLastPlayedTrailerID() == -1) trailerID else mainViewModel.getLastPlayedTrailerID()
                (it.player as ExoPlayer).seekTo(mediaItemIndex,playerPos)
                when (orient) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    }
                    else -> {
                        it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                    }
                }
                (it.player as ExoPlayer).playWhenReady = true
            }
        },
        modifier = Modifier
            .fillMaxSize()
    )
}
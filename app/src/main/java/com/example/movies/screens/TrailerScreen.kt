package com.example.movies.screens

import android.media.browse.MediaBrowser
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movies.WindowInfo

@androidx.annotation.OptIn(UnstableApi::class) @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailerScreen(
    navController: NavController,
    windowInfo: WindowInfo,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movie trailer", fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(route = "MainScreen") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        },
    ) { values ->
        val playWhenReady by remember { mutableStateOf(true) }
        val context = LocalContext.current
        val mediaItem =
            MediaItem.fromUri("https://fwcdn.pl/video/f/36/936/gladiator___official__trailer__hd_.h265.1080p.mp4")
        // Create a data source factory.
//        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
        // Create a HLS media source pointing to a playlist uri.
//        val hlsMediaSource =
//            HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri("https://fwcdn.pl/video/f/36/936/gladiator___official__trailer__hd_.h265.1080p.mp4"))
        // Set the HLS media source as the playlist with a single media item.

        val playerView = PlayerView(context)
        val player = ExoPlayer.Builder(context).build()

        player.setMediaItem(mediaItem)
//        player.setMediaSource(hlsMediaSource)
        playerView.player = player
        LaunchedEffect(player) {
            player.prepare()
            player.playWhenReady = playWhenReady
        }
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
            factory = {
                playerView
            }
        )
    }
}

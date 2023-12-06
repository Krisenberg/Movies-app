package com.example.movies.screens

import android.content.Context
import android.media.browse.MediaBrowser
import android.net.VpnService.prepare
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movies.MainViewModel
import com.example.movies.R
import com.example.movies.WindowInfo

@androidx.annotation.OptIn(UnstableApi::class) @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailerScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
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
                    IconButton(onClick = {
                        mainViewModel.setPreviousOrientation()
                        navController.navigate(route = "MainScreen")
                    } ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        },
    ) { values ->
//        val playWhenReady by remember { mutableStateOf(true) }
//        val context = LocalContext.current
//        val mediaItem =
//            MediaItem.fromUri("https://fwcdn.pl/video/f/36/936/gladiator___official__trailer__hd_.h265.1080p.mp4")
//        // Create a data source factory.
////        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
//        // Create a HLS media source pointing to a playlist uri.
////        val hlsMediaSource =
////            HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri("https://fwcdn.pl/video/f/36/936/gladiator___official__trailer__hd_.h265.1080p.mp4"))
//        // Set the HLS media source as the playlist with a single media item.
//
//        val playerView = PlayerView(context)
//        val player = ExoPlayer.Builder(context).build()
//
//        player.setMediaItem(mediaItem)
////        player.setMediaSource(hlsMediaSource)
//        playerView.player = player
//        LaunchedEffect(player) {
//            player.prepare()
//            player.playWhenReady = playWhenReady
//        }
//        AndroidView(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(values),
//            factory = {
//                playerView
//            }
//        )
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(values),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ){
//            VideoPlayer(
//                initialTrailerIndex = 0
//            )
//        }

        val movies = listOf(
            "https://fwcdn.pl/video/f/165/1065/the_lord_of_the_rings_the_fellowship_of_the_ring_trailer_2__2001_.vp9.720p.webm",
            "https://fwcdn.pl/video/f/251/31451/the_lord_of_the_rings_the_two_towers___official__trailer__hd_.h265.720p.mp4",
            "https://fwcdn.pl/video/f/141/11841/the_lord_of_the_rings_the_return_of_the_king___official__trailer__hd_.h265.720p.mp4",
            "https://fwcdn.pl/video/trailer/Oppenheimer___New_Trailer.h265.1080p.mp4",
            "https://fwcdn.pl/video/120749/auta_zwiastun_pl.360p.mp4",
            "https://fwcdn.pl/video/f/104/33404/shrek_2___official__trailer__hd_.vp9.1080p.webm",
            "https://fwcdn.pl/video/f/242/107642/casino_royale_trailer_hd.vp9.1080p.webm",
            "https://fwcdn.pl/video/f/159/759/sw_revised.h265.720p.mp4",
            "https://fwcdn.pl/video/f/36/936/gladiator___official__trailer__hd_.h265.1080p.mp4",
        )

        val context = LocalContext.current
        val exoPlayer = ExoPlayer.Builder(context).build()
        for (trailerUri in movies) {
            val mediaItem = MediaItem.fromUri(trailerUri)
            exoPlayer.addMediaItem(mediaItem)
        }

        exoPlayer.prepare()
//        exoPlayer.seekTo(0, 0)

        val playerView = PlayerView(context)
        playerView.player = exoPlayer

        var showDialog by rememberSaveable { mutableStateOf(false) }
        var showDialogTrailerID by rememberSaveable { mutableIntStateOf(0) }
        var playerPosition by rememberSaveable { mutableLongStateOf(0) }

        val moviesImgTitleList = mainViewModel.getMoviesImagesTitles()

        LazyColumn(contentPadding = values) {
            items(
                count = moviesImgTitleList.size,
                key = {
                    moviesImgTitleList[it].second
                },
                itemContent = { index ->
                    ColumnItemTrailer(
                        movieImgID = moviesImgTitleList[index].first,
                        movieTitleID = moviesImgTitleList[index].second,
                        itemIndex = index,
                        onShowDialogChange = { newValue -> showDialog = newValue },
                        onShowDialogTrailerIDChange = { newValue -> showDialogTrailerID = newValue },
                        modifier = modifier)
                }
            )
        }
        if (showDialog) {
            ZoomedTrailerDialog(
                playerView = playerView,
                trailerID = showDialogTrailerID,
                playerPosition = playerPosition,
                onPlayerPositionChange = { newPosition -> playerPosition = newPosition },
                onDismissRequest = { showDialog = false; exoPlayer.release() })
        }
    }
}

@Composable
fun ColumnItemTrailer(
    movieImgID: Int,
    movieTitleID: Int,
    itemIndex: Int,
    onShowDialogChange: (Boolean) -> Unit,
    onShowDialogTrailerIDChange: (Int) -> Unit,
    modifier: Modifier
){
    Card(
        modifier
            .padding(8.dp)
            .wrapContentSize()
            .clickable {
                onShowDialogChange(true)
                onShowDialogTrailerIDChange(itemIndex)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row (
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = movieImgID),
                contentDescription = stringResource(id = R.string.dummy_desc),
                modifier
                    .fillMaxWidth(0.5f)
                //.size(width = 172.dp, height = 97.dp)
            )
            Text(
                text = stringResource(id = movieTitleID),
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }
}

@androidx.annotation.OptIn(UnstableApi::class) @Composable
fun ZoomedTrailerDialog(
    playerView: PlayerView,
    trailerID: Int,
    playerPosition: Long,
    onPlayerPositionChange: (Long) -> Unit,
    onDismissRequest: () -> Unit,
) {
    DisposableEffect(Unit) {
        onDispose {
            onPlayerPositionChange(playerView.player!!.currentPosition)
            playerView.player!!.pause()
        }
    }

    Dialog(
            onDismissRequest = {
                onDismissRequest()
            },
            properties = DialogProperties().let {
                DialogProperties(
                    dismissOnBackPress = it.dismissOnBackPress,
                    dismissOnClickOutside = it.dismissOnClickOutside,
                    securePolicy = it.securePolicy,
                    usePlatformDefaultWidth = false
                )
            },
    ) {
//        val context = LocalContext.current

//        player.seekTo(trailerID,0)

//        val playerView = PlayerView(context)
//        playerView.player = player
        playerView.useController = true
        playerView.player?.seekTo(trailerID, playerPosition)
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
        playerView.keepScreenOn = true

        var lifecycle by remember {
            mutableStateOf(Lifecycle.Event.ON_CREATE)
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
                .aspectRatio(16 / 9f)
        )
    }
}

//@Composable
//fun VideoPlayer(
//    initialTrailerIndex: Int,
//) {
//    val context = LocalContext.current
//    var currentTrailerIndex by remember { mutableStateOf(initialTrailerIndex) }
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build()
//    }
//
//    fun playVideoAtIndex(index: Int) {
//        if (index in movies.indices) {
//            currentTrailerIndex = index
//            preparePlayer(exoPlayer, movies[index])
//        }
//    }
//
//    if(movies.size > 1){
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Button(onClick = { playVideoAtIndex(currentTrailerIndex - 1) }) {
//                Text("Previous")
//            }
//            Button(onClick = { playVideoAtIndex(currentTrailerIndex + 1) }) {
//                Text("Next")
//            }
//        }
//    }
//
//
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .fillMaxHeight()) {
//        AndroidView(
//            factory = { context ->
//                PlayerView(context).apply {
//                    player = exoPlayer
//                    useController = true
//                    layoutParams = FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT
//                    )
//                }
//            },
//            modifier = Modifier
//                .fillMaxSize()
//                .fillMaxHeight()
//        )
//    }
//
//    DisposableEffect(key1 = exoPlayer) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//}
//
//@androidx.annotation.OptIn(UnstableApi::class)
//private fun preparePlayer(exoPlayer: ExoPlayer, videoUri: String) {
//    val mediaItem = MediaItem.fromUri(videoUri)
//    exoPlayer.setMediaItem(mediaItem)
//    exoPlayer.prepare()
//}


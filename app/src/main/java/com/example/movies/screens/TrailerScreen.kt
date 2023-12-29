package com.example.movies.screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movies.MainViewModel
import com.example.movies.R


@Composable
fun CardItemTrailerContent(
    mainViewModel: MainViewModel,
    trailerImgID: Int,
    movieTitleID: Int,
    itemIndex: Int,
    onShowDialogChange: () -> Unit,
    onShowDialogTrailerIDChange: (Int) -> Unit,
    navController: NavController,
    modifier: Modifier
){
    var isExpandedCard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .wrapContentSize()
    ){
        Row (
            modifier
                .wrapContentSize()
                .clickable {
                    isExpandedCard = !isExpandedCard
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val rotationState by animateFloatAsState(
                targetValue = if (isExpandedCard) 180f else 0f
            )
            Image(
                painter = painterResource(id = trailerImgID),
                contentDescription = stringResource(id = R.string.dummy_desc),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .width(150.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Text(
                text = stringResource(id = movieTitleID),
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(start = 4.dp)
            )
            IconButton(
                modifier = Modifier
                    .alpha(0.8f)
                    .weight(1f)
                    .rotate(rotationState),
                onClick = {
                    isExpandedCard = !isExpandedCard
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop-Down Arrow"
                )
            }
        }
        if (isExpandedCard) {
            Row (
                modifier
                    .wrapContentSize()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Row (
                    modifier
                        .fillMaxWidth(0.5f)
                        .height(50.dp)
                        .clickable {
                            mainViewModel.setLastPlayedTrailerID(-1)
                            onShowDialogChange()
                            onShowDialogTrailerIDChange(itemIndex)
                        }
                        .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(18.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.FullscreenExit,
                        contentDescription = "Preview icon"
                    )
                    Text(
                        text = "Preview",
                        fontSize = 28.sp,
                        modifier = Modifier
                    )
                }
                Row (
                    modifier
                        .fillMaxWidth(1f)
                        .height(50.dp)
                        .clickable {
                            mainViewModel.setLastPlayedTrailerID(-1)
                            navController.navigate(route = "FullscreenTrailerScreen/$itemIndex")
                        }
                        .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(18.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Fullscreen,
                        contentDescription = "Fullscreen icon"
                    )
                    Text(
                        text = "Fullscreen",
                        fontSize = 28.sp,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@androidx.annotation.OptIn(UnstableApi::class) @Composable
fun ZoomedTrailerDialog(
    mainViewModel: MainViewModel,
    trailerID: Int,
    onDismissRequest: () -> Unit,
) {
    val orient = LocalConfiguration.current.orientation

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {

        onDispose {
            mainViewModel.setLastPlayedTrailerOrientation(orient)
            mainViewModel.setLastPlayedTrailerPosition(mainViewModel.player.currentPosition)
            mainViewModel.setLastPlayedTrailerID(mainViewModel.player.currentMediaItemIndex)
        }
    }


    Dialog(
            onDismissRequest = {
                onDismissRequest()
                mainViewModel.setLastPlayedTrailerOrientation(orient)
                mainViewModel.setLastPlayedTrailerPosition(mainViewModel.player.currentPosition)
                mainViewModel.setLastPlayedTrailerID(mainViewModel.player.currentMediaItemIndex)
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

        AndroidView(
            factory = { context ->
                PlayerView(context).also {
                    it.player = mainViewModel.player
                    it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                    it.useController = true
                    (it.player as ExoPlayer).playWhenReady = true

                    var playerPos: Long = 0
                    if (mainViewModel.getLastPlayedTrailerID() != -1 && mainViewModel.getLastPlayedTrailerOrientation() != orient)
                        playerPos = mainViewModel.getLastPlayedTrailerPosition()

                    val mediaItemIndex = if (mainViewModel.getLastPlayedTrailerID() == -1) trailerID else mainViewModel.getLastPlayedTrailerID()
                    (it.player as ExoPlayer).seekTo(mediaItemIndex,playerPos)
                } },
            modifier = Modifier
                .aspectRatio(16 / 9f)
        )
    }
}


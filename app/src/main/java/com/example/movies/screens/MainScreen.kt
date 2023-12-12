package com.example.movies.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.ContentPasteSearch
import androidx.compose.material.icons.outlined.Details
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movies.MainViewModel
import com.example.movies.R
import com.google.android.material.tabs.TabItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
){
    val tabItems = listOf(
        TabItem(
            title = "Details",
            unselectedIcon = Icons.Outlined.ContentPasteSearch,
            selectedIcon = Icons.Filled.ContentPasteSearch
        ),
        TabItem(
            title = "Trailers",
            unselectedIcon = Icons.Outlined.VideoLibrary,
            selectedIcon = Icons.Filled.VideoLibrary
        )
    )

    var selectedTabIndex by remember { mutableIntStateOf(mainViewModel.selectedMainScreenTabIndex()) }
    var isExpandedCard by remember { mutableStateOf(mainViewModel.isExpandedTrailerCard()) }
    var expandedCardIndex by remember { mutableStateOf(mainViewModel.expandedTrailerCardIndex()) }

    val pagerState = rememberPagerState {
        tabItems.size
    }
    
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
            mainViewModel.selectedMainScreenTabIndex(pagerState.currentPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed {index, tabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        mainViewModel.selectedMainScreenTabIndex(index)
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = tabItem.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                tabItem.selectedIcon
                            } else tabItem.unselectedIcon,
                            contentDescription = tabItem.title
                        )
                    }
                )
            }
        }
        
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {index ->
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                if (index == 0) {
                    val moviesImgTitleList = mainViewModel.getMoviesImagesTitles()
                    LazyColumn {
                        val itemCount = moviesImgTitleList.size
                        items(itemCount) {
                            ColumnItem(
                                movieImgID = moviesImgTitleList[it].first,
                                movieTitleID = moviesImgTitleList[it].second,
                                itemIndex = it,
                                navController = navController,
                                expandedState = mainViewModel.isExpandedTrailerCard(),
                                isExpandedCardChange = {
                                    isExpandedCard = !isExpandedCard
                                    mainViewModel.isExpandedTrailerCard(!isExpandedCard)
                                },
                                expandedCardIndex = {index ->
                                    expandedCardIndex = index
                                    mainViewModel.expandedTrailerCardIndex(index)
                                },
                                modifier = modifier)
                        }
                    }
                }
                if (index == 1) {
                    var isExpandedCard by remember { mutableStateOf(mainViewModel.isExpandedTrailerCard()) }
                    var expandedCardIndex by remember { mutableIntStateOf(mainViewModel.expandedTrailerCardIndex()) }

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

                    LazyColumn{
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
        }
    }
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = stringResource(id = R.string.list_header), fontSize = 24.sp)
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        mainViewModel.setOrientLand()
//                        navController.navigate(route = "TrailerScreen")
//                    }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Go back"
//                        )
//                    }
//                }
//            )
//        },
//    ){ values ->
//        val moviesImgTitleList = mainViewModel.getMoviesImagesTitles()
//        LazyColumn(contentPadding = values) {
//            val itemCount = moviesImgTitleList.size
//            items(itemCount) {
//                ColumnItem(
//                    movieImgID = moviesImgTitleList[it].first,
//                    movieTitleID = moviesImgTitleList[it].second,
//                    itemIndex = it,
//                    navController = navController,
//                    modifier = modifier)
//            }
//        }
//    }
}

@Composable
fun ColumnItem(
    movieImgID: Int,
    movieTitleID: Int,
    itemIndex: Int,
    navController: NavController,
    expandedState: Boolean,
    isExpandedCardChange: () -> Unit,
    expandedCardIndex: (Int) -> Unit,
    modifier: Modifier
){
    var isExpandedCard by remember { mutableStateOf(expandedState) }
    Card(
        modifier
            .padding(8.dp)
            .wrapContentSize()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clickable {
                //navController.navigate(route = "DetailsScreen/$itemIndex")
                isExpandedCard = !isExpandedCard
                isExpandedCardChange()
                expandedCardIndex(itemIndex)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Row (
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val rotationState by animateFloatAsState(
                    targetValue = if (isExpandedCard) 180f else 0f
                )
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
                IconButton(
                    modifier = Modifier
                        .alpha(0.8f)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        isExpandedCard = !isExpandedCard
                        isExpandedCardChange()
                        expandedCardIndex(itemIndex)
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )

                }
            }
            if (isExpandedCard)
                Text (
                    text = "Some dummy text"
                )
        }
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)
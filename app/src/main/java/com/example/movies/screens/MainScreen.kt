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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movies.ContentManager
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
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

//    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
//        if(!pagerState.isScrollInProgress) {
//            selectedTabIndex = pagerState.currentPage
//            mainViewModel.selectedMainScreenTabIndex(pagerState.currentPage)
//        }
////        selectedTabIndex = pagerState.currentPage
////        mainViewModel.selectedMainScreenTabIndex(pagerState.currentPage)
//    }

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
                                modifier = modifier)
                        }
                    }
                }
                if (index == 1) {
                    var isExpandedCard by remember { mutableStateOf(mainViewModel.isExpandedTrailerCard()) }
//                    var isExpandedCard by remember { mutableStateOf(false) }
                    var expandedCardIndex by remember { mutableIntStateOf(mainViewModel.expandedTrailerCardIndex()) }
                    var showDialog by rememberSaveable { mutableStateOf(false) }
                    var showDialogTrailerID by rememberSaveable { mutableIntStateOf(0) }

//                    expandedState = mainViewModel.isExpandedTrailerCard(),
//                    isExpandedCardChange = {
//                        isExpandedCard = !isExpandedCard
//                        mainViewModel.isExpandedTrailerCard(!isExpandedCard)
//                    },
//                    expandedCardIndex = {index ->
//                        expandedCardIndex = index
//                        mainViewModel.expandedTrailerCardIndex(index)
//                    },

//                    val movies = mainViewModel.getMovieTrailers()
//
//                    val context = LocalContext.current
//                    val exoPlayer = ExoPlayer.Builder(context).build()
//                    for (trailerUri in movies) {
//                        val mediaItem = MediaItem.fromUri(trailerUri)
//                        exoPlayer.addMediaItem(mediaItem)
//                    }
//                    exoPlayer.prepare()
//
//                    val playerView = PlayerView(context)
//                    playerView.player = exoPlayer

                    val trailersImgTitleList = mainViewModel.getMovieTrailersCardsData()

                    LazyColumn{
                        items(
                            count = trailersImgTitleList.size,
                            key = {
                                trailersImgTitleList[it].second
                            },
                            itemContent = { itemIndex ->
                                CardItemTrailer(
                                    mainViewModel = mainViewModel,
                                    trailerImgID = trailersImgTitleList[itemIndex].first,
                                    movieTitleID = trailersImgTitleList[itemIndex].second,
                                    itemIndex = itemIndex,
                                    expandedState = isExpandedCard,
                                    isExpandedCardChange = {
                                        isExpandedCard = !isExpandedCard
                                        mainViewModel.isExpandedTrailerCard(!isExpandedCard)
                                    },
                                    expandedCardIndex = { newIndex ->
                                        expandedCardIndex = newIndex
                                        mainViewModel.expandedTrailerCardIndex(newIndex)
                                    },
                                    onShowDialogChange = { showDialog = true },
                                    onShowDialogTrailerIDChange = { newValue -> showDialogTrailerID = newValue },
                                    navController = navController,
                                    modifier = Modifier
                                )
                            }
                        )
                    }
//                    if (showDialog) {
//                        ZoomedTrailerDialog(
//                            player = exoPlayer,
//                            playerView = playerView,
//                            trailerID = showDialogTrailerID,
//                            onDismissRequest = { showDialog = false; exoPlayer.release() }
//                        )
//                    }
                }
            }
        }
    }
///////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////
}

@Composable
fun ColumnItem(
    movieImgID: Int,
    movieTitleID: Int,
    itemIndex: Int,
    navController: NavController,
    modifier: Modifier
){
    Card(
        modifier
            .padding(8.dp)
            .wrapContentSize()
            .clickable {
                navController.navigate(route = "DetailsScreen/$itemIndex")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Row (
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = movieImgID),
                contentDescription = "Movie image",
                modifier
                    .fillMaxWidth(0.5f)
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

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun MoviesListPreview() {
//    val moviesData = ContentManager.getDatabaseData()
//    val imagesList = moviesData.map { it.trailerImage }
//    val titlesList = moviesData.map { it.titleAbbrev }
//    val moviesImgTitleList = imagesList.zip(titlesList)
//    val index = 0
//
//    var showDialog by rememberSaveable { mutableStateOf(false) }
//    var showDialogTrailerID by rememberSaveable { mutableIntStateOf(0) }
//    var selectedTabIndex by remember { mutableIntStateOf(0) }
//    var isExpandedCard by remember { mutableStateOf(true) }
//    var expandedCardIndex by remember { mutableStateOf(0) }
//
//    CardItemTrailer(
//        trailerImgID = moviesImgTitleList[index].first,
//        movieTitleID = moviesImgTitleList[index].second,
//        itemIndex = index,
//        expandedState = isExpandedCard,
//        isExpandedCardChange = { isExpandedCard = !isExpandedCard },
//        expandedCardIndex = { newIndex -> expandedCardIndex = index},
//        onShowDialogChange = { showDialog = true },
//        onShowDialogTrailerIDChange = { newValue -> showDialogTrailerID = newValue },
//        modifier = Modifier
//    )
}
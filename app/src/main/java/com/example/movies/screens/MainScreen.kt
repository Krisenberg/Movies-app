package com.example.movies.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.ContentPasteSearch
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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

    var selectedTabIndex by remember { mutableIntStateOf(mainViewModel.getSelectedMainScreenTabIndex()) }
    val pagerState = rememberPagerState {
        tabItems.size
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        if (selectedTabIndex != pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
            mainViewModel.setSelectedMainScreenTabIndex(pagerState.currentPage)
        }
    }

    LaunchedEffect(selectedTabIndex) {
        if (selectedTabIndex != pagerState.currentPage) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(selectedTabIndex)
            }
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
                        mainViewModel.setSelectedMainScreenTabIndex(index)
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
                    var showDialog by rememberSaveable { mutableStateOf(false) }
                    var showDialogTrailerID by rememberSaveable { mutableIntStateOf(0) }

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
                                    onShowDialogChange = { showDialog = true },
                                    onShowDialogTrailerIDChange = { newValue -> showDialogTrailerID = newValue },
                                    navController = navController,
                                    modifier = Modifier
                                )
                            }
                        )
                    }
                    if (showDialog) {
                        ZoomedTrailerDialog(
                            mainViewModel = mainViewModel,
                            trailerID = showDialogTrailerID,
                            onDismissRequest = { showDialog = false; mainViewModel.player.pause() }
                        )
                    }
                }
            }
        }
    }
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
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .width(150.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp))
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

@Composable
fun CardItemTrailer(
    mainViewModel: MainViewModel,
    trailerImgID: Int,
    movieTitleID: Int,
    itemIndex: Int,
    onShowDialogChange: () -> Unit,
    onShowDialogTrailerIDChange: (Int) -> Unit,
    navController: NavController,
    modifier: Modifier
){
    Card(
        modifier
            .padding(8.dp)
            .wrapContentSize()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearOutSlowInEasing
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        CardItemTrailerContent(
            mainViewModel = mainViewModel,
            trailerImgID = trailerImgID,
            movieTitleID = movieTitleID,
            itemIndex = itemIndex,
            onShowDialogChange = onShowDialogChange,
            onShowDialogTrailerIDChange = onShowDialogTrailerIDChange,
            navController = navController,
            modifier = modifier
        )
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)
package com.example.movies.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    moviesImgTitleList: List<Pair<Int, Int>>,
    navController: NavController,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.list_header))
                },
            )
        },
    ){ values ->
        LazyColumn(contentPadding = values) {
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
//    LazyColumn(contentPadding = PaddingValues(15.dp)) {
//        val itemCount = moviesImgTitleList.size
//        items(itemCount) {
//            ColumnItem(
//                movieImgID = moviesImgTitleList[it].first,
//                movieTitleID = moviesImgTitleList[it].second,
//                itemIndex = it,
//                navController = navController,
//                modifier = modifier)
//        }
//    }
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
                    .size(width = 172.dp, height = 97.dp)
            )
            Text(
                text = stringResource(id = movieTitleID),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
        }
    }
}
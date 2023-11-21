package com.example.movies.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.R

@Composable
fun DetailsScreen(
    moviesImgTitleList: List<Pair<Int, Int>>,
    itemIndex: Int?,
    modifier: Modifier = Modifier
){
    Column (
        modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Box (
            modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image (
                painter = painterResource(id = moviesImgTitleList[itemIndex!!].first),
                contentDescription = stringResource(id = R.string.dummy_desc)
            )
        }
    }
}
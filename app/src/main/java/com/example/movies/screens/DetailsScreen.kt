package com.example.movies.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    moviesImgTitleList: List<Pair<Int, Int>>,
    itemIndex: Int?,
    navController: NavController,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Details")
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(route = "MainScreen")}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        },
    ) { values ->
        var showDialog by remember { mutableStateOf(false) }
        var showScenes by remember { mutableStateOf(true) }
        var showActors by remember { mutableStateOf(false) }
        Column(
            modifier
                .fillMaxSize()
                .padding(values),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
            ) {
                Column (
                    modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 8.dp),
                ){
                    Image(
                        painter = painterResource(id = moviesImgTitleList[itemIndex!!].first),
                        contentDescription = stringResource(id = R.string.dummy_desc),
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { showDialog = true },
                    )

                    if (showDialog) {
                        ZoomedImageDialog(
                            imageRes = moviesImgTitleList[itemIndex!!].first,
                            onDismissRequest = { showDialog = false }
                        )
                    }
                    Column (
                        modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                    ){
                        Text(text = "Duration XYZ")
                        Text(text = "Directed by: me")
                    }
                }
                Column (
                    modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    ) {
                    Text(text = "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                            "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                            "Lorem ipsum Lorem ipsum")
                }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 12.dp, end = 8.dp, bottom = 12.dp),
            ) {
                TextButton(
                    onClick = {
                        showScenes = true
                        showActors = false
                    },
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = modifier
                                .fillMaxWidth(0.5f)
                    ) {
                    Text(
                        text = "Scenes",
                        fontSize = 30.sp,
                        color = Color.Black
                    )
                }
                TextButton(
                    onClick = {
                        showScenes = false
                        showActors = true
                    },
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Actors",
                        fontSize = 30.sp,
                        color = Color.Black
                    )
                }
            }
//            val actorsDummyList = mutableListOf<String>()
//            for (i in 1..10) {
//                actorsDummyList.add("Actor no $i")
//            }
//            Column (
//                modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                actorsDummyList.forEach { actorData ->
//                    Text(text = actorData)
//                }
//            }
                Box (
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ){
                    val scenesDummyList = mutableListOf<Int>()
                    for (i in 1..16) {
                        scenesDummyList.add(R.drawable.main_img_cars)
                    }
                    val actorsDummyList = mutableListOf<String>()
                    for (i in 1..10) {
                        actorsDummyList.add("Actor no $i")
                    }
                    if (showScenes) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            items(scenesDummyList.size) { photo ->
                                PhotoItem(photo)
                            }
                        }
                    }
                    if (showActors) {
                        Column (
                            modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            actorsDummyList.forEach { actorData ->
                                Text(text = actorData)
                            }
                        }
                    }
                }
        }
    }
}

@Composable
fun ZoomedImageDialog(
    imageRes: Int,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            shape = RoundedCornerShape(4.dp),
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ShowActors(
    actorsList: List<String>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        actorsList.forEach { actorData ->
            Text(text = actorData)
        }
    }
}

@Composable
fun ShowScenes(
    scenesList: List<Int>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(scenesList.size) { photo ->
            PhotoItem(photo)
        }
    }
}

@Composable
fun PhotoItem(photo: Int) {
    Image(
        painter = painterResource(id = R.drawable.main_img_lotr_1),
        contentDescription = null,
        modifier = Modifier
            .aspectRatio(1f)
            .padding(5.dp)
            .clip(RoundedCornerShape(4.dp)),
        contentScale = ContentScale.Crop
    )
}

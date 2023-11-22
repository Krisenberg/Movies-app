package com.example.movies.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.movies.MovieDetails
import com.example.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieDetails: MovieDetails,
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
        var showDialogImg by remember { mutableIntStateOf(0) }
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
                        painter = painterResource(id = movieDetails.mainImage),
                        contentDescription = stringResource(id = R.string.dummy_desc),
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {
                                showDialog = true
                                showDialogImg = movieDetails.mainImage
                            },
                    )
                    Column (
                        modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                    ){
                        movieDetails.details.forEach {
                            detail -> Text(text = detail)
                        }
                    }
                }
                Column (
                    modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    ) {
                    Text(text = movieDetails.description)
                }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 12.dp, end = 8.dp, bottom = 12.dp),
            ) {
                val buttonScenesBackgroundColor = if (showScenes) R.color.pastel_pink else R.color.white
                val buttonActorsBackgroundColor = if (showActors) R.color.pastel_pink else R.color.white
                TextButton(
                    onClick = {
                        showScenes = true
                        showActors = false
                    },
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = modifier
                        .fillMaxWidth(0.5f)
                        .background(colorResource(id = buttonScenesBackgroundColor))
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
                        .background(colorResource(id = buttonActorsBackgroundColor))
                ) {
                    Text(
                        text = "Actors",
                        fontSize = 30.sp,
                        color = Color.Black
                    )
                }
            }
            Box (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ){
//                val scenesDummyList = mutableListOf<Int>()
//                for (i in 1..16) {
//                    scenesDummyList.add(R.drawable.main_img_cars)
//                }
//                val actorsDummyList = mutableListOf<String>()
//                for (i in 1..10) {
//                    actorsDummyList.add("Actor no $i")
//                }
                if (showScenes) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        items(movieDetails.scenes) { photo ->
                            PhotoItem(
                                photo = photo,
                                onShowDialogChange = { newValue -> showDialog = newValue },
                                onShowDialogImgChange = { newValue -> showDialogImg = newValue }
                            )
                        }
                    }
                }
                if (showActors) {
                    Column (
                        modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        movieDetails.actors.forEach { actorData ->
                            Text(text = actorData)
                        }
                    }
                }
            }
        }
        if (showDialog) {
            ZoomedImageDialog(
                imageRes = showDialogImg,
                onDismissRequest = { showDialog = false }
            )
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
fun PhotoItem(
    photo: Int,
    onShowDialogChange: (Boolean) -> Unit,
    onShowDialogImgChange: (Int) -> Unit
) {
    Image(
        painter = painterResource(id = photo),
        contentDescription = null,
        modifier = Modifier
            .aspectRatio(1f)
            .padding(5.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onShowDialogChange(true)
                onShowDialogImgChange(photo)
            },
        contentScale = ContentScale.Crop
    )
}

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.movies.MovieDetails
import com.example.movies.R
import com.example.movies.WindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieDetails: MovieDetails,
    navController: NavController,
    windowInfo: WindowInfo,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = movieDetails.titleAbbrev))
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
        var showDialogTxt by remember { mutableStateOf(false) }
        var showScenes by remember { mutableStateOf(true) }
        var showActors by remember { mutableStateOf(false) }
        Column(
            modifier
                .fillMaxSize()
                .padding(values)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                Row(
                    modifier
                        .fillMaxWidth()
                        //.height(110.dp)
                        .wrapContentHeight()
                        .padding(start = 12.dp),
                ){
                    MainImgItem(
                        imageID = movieDetails.mainImage,
                        onShowDialogChange = { newValue -> showDialog = newValue },
                        onShowDialogImgChange = { newValue -> showDialogImg = newValue }
                    )
                    DescriptionBox(descriptionID = movieDetails.description, onShowDialogTxtChange = { newValue -> showDialogTxt = newValue } )
                }
                DetailsItem(detailsList = movieDetails.details)
            } else {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                ) {
                    MainImgItem(
                        imageID = movieDetails.mainImage,
                        onShowDialogChange = { newValue -> showDialog = newValue },
                        onShowDialogImgChange = { newValue -> showDialogImg = newValue }
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(1f)
                    ) {
                        DescriptionBox(descriptionID = movieDetails.description, onShowDialogTxtChange = { newValue -> showDialogTxt = newValue } )
                        DetailsItem(detailsList = movieDetails.details)
                    }
                }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 12.dp, end = 8.dp, bottom = 12.dp),
            ) {
                val buttonScenesBackgroundColor = if (showScenes) R.color.pastel_pink else R.color.white
                val buttonActorsBackgroundColor = if (showActors) R.color.pastel_pink else R.color.white
                ChooseButton(
                    scenesTrigger = true,
                    actorsTrigger = false,
                    onClickScenesDialog = { newValue -> showScenes = newValue },
                    onClickActorsDialog = { newValue -> showActors = newValue },
                    backgroundColor = buttonScenesBackgroundColor,
                    textID = R.string.scenes_button,
                    widthFraction = 0.5f,
                    sideIsLeft = true
                )
                ChooseButton(
                    scenesTrigger = false,
                    actorsTrigger = true,
                    onClickScenesDialog = { newValue -> showScenes = newValue },
                    onClickActorsDialog = { newValue -> showActors = newValue },
                    backgroundColor = buttonActorsBackgroundColor,
                    textID = R.string.actors_button,
                    widthFraction = 1.0f,
                    sideIsLeft = false
                )
            }
            Box (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ){
                if (showScenes) {
                    NonlazyGrid(
                        columns = 3,
                        itemCount = movieDetails.scenes.size,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    ) {
                        PhotoItem(
                            photo = movieDetails.scenes[it],
                            onShowDialogChange = { newValue -> showDialog = newValue },
                            onShowDialogImgChange = { newValue -> showDialogImg = newValue }
                        )
                    }
                }
                if (showActors) {
                    ActorsList(actorsList = movieDetails.actors)
                }
            }
        }

        if (showDialog) {
            ZoomedImageDialog(
                imageRes = showDialogImg,
                onDismissRequest = { showDialog = false }
            )
        }

        if (showDialogTxt) {
            ZoomedTextDialog(
                textRes = movieDetails.description,
                onDismissRequest = { showDialogTxt = false }
            )
        }
    }
}

@Composable
fun MainImgItem(
    imageID: Int,
    onShowDialogChange: (Boolean) -> Unit,
    onShowDialogImgChange: (Int) -> Unit
){
    Image(
        painter = painterResource(id = imageID),
        contentDescription = stringResource(id = R.string.dummy_desc),
        modifier = Modifier
            .fillMaxWidth(0.475f)
            //.fillMaxHeight()
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onShowDialogChange(true)
                onShowDialogImgChange(imageID)
            },
    )
}

@Composable
fun DescriptionBox(
    descriptionID: Int,
    onShowDialogTxtChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier
//            .fillMaxWidth()
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.pink_gray))
            .clickable { onShowDialogTxtChange(true) }
    ) {
        Column(
            modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.description_header),
                textAlign = TextAlign.Left,
                fontStyle = FontStyle.Italic,
                modifier = modifier.padding(top = 5.dp, start = 5.dp, bottom = 15.dp)
            )
            Text(
                text = stringResource(id = descriptionID).substring(0,40) + "...",
                fontSize = 14.sp,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            )
        }
    }
}
@Composable
fun DetailsItem(
    detailsList: List<Int>,
    modifier: Modifier = Modifier
){
    Column (
        modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    ){
        detailsList.forEach {
                detail ->
            val text = stringResource(id = detail)
            val parts = text.split(':', limit = 2) // Split the text at the first ':'
            if (parts.size == 2) {
                // Display the part before ':' in bold and the rest normally
                Row {
                    Text(
                        text = parts[0] + ":",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Text(text = parts[1])
                }
            } else {
                // If ':' is not found, display the entire text normally
                Text(text = text)
            }
        }
    }
}

@Composable
fun ChooseButton(
    scenesTrigger: Boolean,
    actorsTrigger: Boolean,
    onClickScenesDialog: (Boolean) -> Unit,
    onClickActorsDialog: (Boolean) -> Unit,
    backgroundColor: Int,
    textID: Int,
    widthFraction: Float,
    sideIsLeft: Boolean,
    modifier: Modifier = Modifier
){
    val shape = if (sideIsLeft) RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                else RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
    TextButton(
        onClick = {
            onClickScenesDialog(scenesTrigger)
            onClickActorsDialog(actorsTrigger)
        },
        shape = shape,
        border = BorderStroke(1.dp, Color.LightGray),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = backgroundColor)),
        modifier = modifier
            .fillMaxWidth(widthFraction)
    ) {
        Text(
            text = stringResource(id = textID),
            fontSize = 30.sp,
            color = Color.Black
        )
    }
}

@Composable
fun ActorsList(
    actorsList: List<Int>,
    modifier: Modifier = Modifier
){
    Column (
        modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 4.dp, end = 20.dp, bottom = 8.dp)
    ) {
        actorsList.forEach { actorData ->
            val stringData = stringResource(id = actorData)

            val openBracketIndex = stringData.indexOf('[')
            val closeBracketIndex = stringData.indexOf(']')

            val realName = if (openBracketIndex != -1) stringData.substring(0, openBracketIndex) else stringData
            val movieName = if (openBracketIndex != -1 && closeBracketIndex != -1 && closeBracketIndex > openBracketIndex)
                stringData.substring(openBracketIndex + 1, closeBracketIndex) else ""

            Column (
                modifier = Modifier.padding(bottom = 8.dp)
            ){
                Text(
                    text = "\u2022" + realName,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.black)
                )
                Text(
                    text = "[ $movieName ]",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.gray),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun NonlazyGrid(
    columns: Int,
    itemCount: Int,
    modifier: Modifier = Modifier,
    content: @Composable (Int) -> Unit
) {
    Column(modifier = modifier) {
        var rows = (itemCount / columns)

        if (itemCount.mod(columns) > 0) {
            rows += 1
        }

        for (rowId in 0 until rows) {
            val firstIndex = rowId * columns

            Row {
                for (columnId in 0 until columns) {
                    val index = firstIndex + columnId
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        if (index < itemCount) {
                            content(index)
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
    onDismissRequest: () -> Unit,
) {
    Dialog( onDismissRequest = { onDismissRequest() } )
    {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth)
    }
//    Box (
//        modifier = Modifier
//            .width(500.dp)
//    ) {
//        Dialog( onDismissRequest = { onDismissRequest() } )
//        {
//            Image(
//                painter = painterResource(id = imageRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(6.dp))
//                    .fillMaxWidth(),
//                contentScale = ContentScale.FillWidth)
//        }
//    }
//    Dialog(
//        onDismissRequest = { onDismissRequest() },
//        content = {
//            Box (
//            modifier = Modifier
//                .fillMaxSize()
//            ) {
//                Image(
//                    painter = painterResource(id = imageRes),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(6.dp))
//                        .fillMaxSize(),
//                    contentScale = ContentScale.Fit
//                )
//            }
//        })
//    ) {
////        Card(
////            modifier = Modifier
//////                .wrapContentWidth()
////                .fillMaxWidth()
////                //.aspectRatio(1f)
//////                .wrapContentHeight()
////                .padding(2.dp),
////            shape = RoundedCornerShape(4.dp),
////        ) {
////            Image(
////                painter = painterResource(id = imageRes),
////                contentDescription = null,
////                modifier = Modifier
////                    .clip(RoundedCornerShape(6.dp))
////                    .fillMaxWidth(), // Maintain aspect ratio// Center the image within the Box
////                contentScale = ContentScale.FillWidth // Fit the image without cropping
////            )
////        }
//        Box (
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Image(
//                painter = painterResource(id = imageRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(6.dp))
//                    .fillMaxSize(),
//                //contentScale = ContentScale.FillHeight
//            )
//        }
////        val horizontalPadding = 2.dp
////        Image(
////            painter = painterResource(id = imageRes),
////            contentDescription = null,
////            modifier = Modifier
////                .clip(RoundedCornerShape(6.dp))
////                .width(windowInfo.screenWidth - (horizontalPadding * 2))
////                .wrapContentHeight(),
////            //contentScale = ContentScale.FillHeight
////        )
//    }
}

@Composable
fun ZoomedTextDialog(
    textRes: Int,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
        ) {
            Box(modifier = Modifier.background(colorResource(id = R.color.pink_gray)))
            {
                Text(
                    text = stringResource(id = textRes),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(12.dp)
                )
            }
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

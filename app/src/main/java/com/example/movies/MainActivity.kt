package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.screens.DetailsScreen
import com.example.movies.screens.MainScreen
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val contentList = ContentManager.GetMainImagesList().zip(ContentManager.GetTitlesList())
        setContent {
            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "MainScreen") {
                            composable(route = "MainScreen") {
                                MainScreen(moviesImgTitleList = contentList, navController)
                            }
                            composable(route = "DetailsScreen/{index}",
                                arguments = listOf(
                                    navArgument(name = "index"){
                                        type = NavType.IntType
                                    }
                                )
                            ){
                                    index ->
                                DetailsScreen(
                                    moviesImgTitleList = contentList,
                                    itemIndex = index.arguments?.getInt("index"),
                                    navController = navController
                                )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SmallTopAppBarExample(navController: NavController) {
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text("Details")
//                },
//                navigationIcon = {
//                    IconButton(onClick = {navController.navigate(route = "MainScreen")}) {
//
//                    }
//                }
//            )
//        },
//    ) { innerPadding -> ScrollConte(innerPadding)
//    }
//}

//@Composable
//fun RowItem(imageID: Int, titleID: Int) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .padding(8.dp)
//            .clip(shape = RoundedCornerShape(16.dp))
//            .background(MaterialTheme.colorScheme.surface)
//    ) {
//        Image(
//            painter = painterResource(id = imageID),
//            contentDescription = stringResource(id = R.string.dummy_desc),
//            contentScale = ContentScale.Fit,
//            modifier = Modifier
//                .requiredSize(width = 192.dp, height = 108.dp)
//        )
//        Text(
//            text = stringResource(id = titleID),
//            color = MaterialTheme.colorScheme.onSurface
//        )
//    }
//}
//
//@Composable
//fun MoviesList(dataList: List<Pair<Int, Int>>, modifier: Modifier = Modifier) {
//    Surface(
//        color = MaterialTheme.colorScheme.primary
//        //modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//    ) {
//        LazyColumn(
//            modifier = Modifier
//                .padding(
//                    start = 16.dp,
//                    end = 16.dp
//                )
//        ) {
//            items(dataList) { tuple ->
//                RowItem(imageID = tuple.second, titleID = tuple.first)
//            }
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun MoviesListPreview() {
//    MoviesTheme {
////        Greeting("Android")
//        MoviesList(MainViewModel().GetMoviesList())
//    }
    MainScreen(moviesImgTitleList = ContentManager.GetMainImagesList().zip(ContentManager.GetTitlesList()), navController = rememberNavController())
}
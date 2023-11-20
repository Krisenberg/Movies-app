package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesList(mainViewModel.GetMoviesList())
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

//@Composable
//fun RowItem(imageID: Int, titleID: Int) {
//    Row {
//        Image(
//            painter = painterResource(id = imageID),
//            contentDescription = stringResource(id = R.string.dummy_desc),
//            contentScale = ContentScale.Fit,
//            modifier = Modifier
//                .size(150.dp)
//        )
//        Text(
//            text = stringResource(id = titleID)
//        )
//    }
//}
//
//@Composable
//fun MoviesList(dataList: List<Pair<Int, Int>>, modifier: Modifier = Modifier) {
//    Surface (
//        color = MaterialTheme.colorScheme.primary,
//        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//    ){
//        LazyColumn {
//            items(dataList) { tuple ->
//                RowItem(imageID = tuple.second, titleID = tuple.first)
//            }
//        }
//    }
//}

@Composable
fun RowItem(imageID: Int, titleID: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Image(
            painter = painterResource(id = imageID),
            contentDescription = stringResource(id = R.string.dummy_desc),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .requiredSize(width = 192.dp, height = 108.dp)
                .padding(8.dp)
        )
        Text(
            text = stringResource(id = titleID),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun MoviesList(dataList: List<Pair<Int, Int>>, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary
        //modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            items(dataList) { tuple ->
                RowItem(imageID = tuple.second, titleID = tuple.first)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesListPreview() {
    MoviesTheme {
//        Greeting("Android")
        MoviesList(MainViewModel().GetMoviesList())
    }
}
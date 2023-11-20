package com.example.movies

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    data class ListEntry (val titleID: Int, val imageID: Int)

//    fun GetMoviesList(): List<Pair<Int, Int>> {
//        val titlesList = ContentManager.GetTitlesList()
//        val imagesList = ContentManager.GetMainImagesList()
//        return titlesList.zip(imagesList)
//    }
    fun GetMoviesList(): List<Pair<Int, Int>> {
        val titlesList = ContentManager.GetTitlesList()
        val imagesList = ContentManager.GetMainImagesList()
        return titlesList.zip(imagesList)
    }
}
package com.example.movies

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private lateinit var moviesData: List<MovieDetails>
    fun init(){
        moviesData = ContentManager.getDatabaseData()
    }

    fun getMoviesImagesTitles(): List<Pair<Int, Int>> {
        val imagesList = moviesData.map { it.mainImage }
        val titlesList = moviesData.map { it.title }
        return imagesList.zip(titlesList)
    }

    fun getMovieDetailsObject(iD: Int): MovieDetails {
         return moviesData[iD]
    }
}
package com.example.movies

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private lateinit var imagesList: List<Int>
    private lateinit var titlesList: List<Int>

    fun init(){
        imagesList = ContentManager.getMainImagesList()
        titlesList = ContentManager.getTitlesList()
    }

    fun getItemsZippedList(): List<Pair<Int, Int>> {
        return imagesList.zip(titlesList)
    }

    fun getMovieDetailsObject(iD: Int): MovieDetails {
        return MovieDetails(
            mainImage = imagesList[iD],
            title = titlesList[iD],
            titleAbbrev = ContentManager.getAbbrev(iD),
            description = ContentManager.getDescription(iD),
            details = ContentManager.getDetails(iD),
            scenes = ContentManager.getScenesList(iD),
            actors = ContentManager.getActorsList(iD)
        )
    }
}
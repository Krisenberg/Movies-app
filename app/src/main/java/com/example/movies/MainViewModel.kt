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
            description = ContentManager.getDescription(iD),
            details = ContentManager.getDetails(iD),
            scenes = ContentManager.getScenesList(iD),
            actors = ContentManager.getActorsList(iD)
        )
    }

//    fun getImagesList(): List<Int> {
//        return imagesList
//    }
//
//    fun getTitlesList(): List<Int> {
//        return titlesList
//    }
//
//    fun getMainImgByID(iD: Int): Int {
//        return imagesList[iD]
//    }
//
//    fun getTitleImgByID(iD: Int): Int {
//        return titlesList[iD]
//    }
//
//    fun getScenesByID(iD: Int): List<Int>{
//        return ContentManager.getScenesList(iD)
//    }
//
//    fun getActorsByID(iD: Int): List<String>{
//        return ContentManager.getActorsList(iD)
//    }
//
//    fun getDetailsByID(iD: Int): List<String>{
//        return ContentManager.getDetails(iD)
//    }
//
//    fun getDescriptionByID(iD: Int): String{
//        return ContentManager.getDescription(iD)
//    }
}
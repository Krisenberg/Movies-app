package com.example.movies

import android.content.pm.ActivityInfo
import android.content.pm.ApplicationInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel(){
    private var previousOrientLandscape = savedStateHandle.get<Boolean>("previousOrientLandscape")
    private val _requestedOrientLandscape = savedStateHandle.getLiveData<Boolean>("_requestedOrientLandscape", false)

    fun init(currentOrientationLand: Boolean){
        previousOrientLandscape = if (previousOrientLandscape==null) currentOrientationLand else previousOrientLandscape
    }

    fun requestedOrientLandscape(): LiveData<Boolean> { return _requestedOrientLandscape }
    fun previousOrientLand(): Boolean { return previousOrientLandscape!! }

    fun setOrientLand() {
        //previousOrientLandscape = _requestedOrientLandscape.value!!
        _requestedOrientLandscape.value = true
    }

    fun setOrientPortrait() {
        previousOrientLandscape = _requestedOrientLandscape.value!!
        _requestedOrientLandscape.value = false
    }

    fun setPreviousOrientation() {
        _requestedOrientLandscape.value = previousOrientLandscape
    }

    fun getMoviesImagesTitles(): List<Pair<Int, Int>> {
        val moviesData = ContentManager.getDatabaseData()
        val imagesList = moviesData.map { it.mainImage }
        val titlesList = moviesData.map { it.titleAbbrev }
        return imagesList.zip(titlesList)
    }

    fun getMovieDetailsObject(iD: Int): MovieDetails {
        val moviesData = ContentManager.getDatabaseData()
        return moviesData[iD]
    }
}
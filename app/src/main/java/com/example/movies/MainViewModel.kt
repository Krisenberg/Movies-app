package com.example.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel(){
//    private var previousOrientLandscape : Boolean? = null
//    private val _requestedOrientLandscape: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
//
//    fun init(currentOrientationLand: Boolean) {
//        previousOrientLandscape =
//            savedStateHandle.get<Boolean>("previousOrientLandscape") ?: currentOrientationLand
//        _requestedOrientLandscape.value =
//            savedStateHandle.get<Boolean>("_requestedOrientLandscape") ?: currentOrientationLand
////        previousOrientLandscape = if (previousOrientLandscape==null) currentOrientationLand else previousOrientLandscape
//    }

    private var previousOrientLandscape = savedStateHandle.get<Boolean>("previousOrientLandscape")
    private var currentOrientLandscape = savedStateHandle.get<Boolean>("currentOrientLandscape")
    private val _requestedOrientLandscape = savedStateHandle.getLiveData<Boolean>("_requestedOrientLandscape", false)
    private var _selectedMainScreenTabIndex = savedStateHandle.get<Int>("_selectedMainScreenTabIndex")
    private var _isExpandedTrailerCard = savedStateHandle.get<Boolean>("_isExpandedTrailerCard")
    private var _expandedTrailerCardIndex = savedStateHandle.get<Int>("_ExpandedTrailerCardIndex")

    private lateinit var changeOrientationToLandscape: () -> Unit
    private lateinit var changeOrientationToPortrait: () -> Unit

    fun init(currentOrientationLand: Boolean, changeOrientToLand: () -> Unit,
             changeOrientToPort: () -> Unit){
        previousOrientLandscape = if (previousOrientLandscape == null) currentOrientationLand else previousOrientLandscape
        currentOrientLandscape = if (currentOrientLandscape == null) currentOrientationLand else currentOrientLandscape
        if (_selectedMainScreenTabIndex == null)
            _selectedMainScreenTabIndex = 0
        if (_isExpandedTrailerCard == null)
            _isExpandedTrailerCard = false
        if (_expandedTrailerCardIndex == null)
            _expandedTrailerCardIndex = 0

        changeOrientationToLandscape = changeOrientToLand
        changeOrientationToPortrait = changeOrientToPort
    }

    fun requestedOrientLandscape(): MutableLiveData<Boolean> { return _requestedOrientLandscape }
    fun previousOrientLand(): Boolean { return previousOrientLandscape!! }

    fun setOrientLand() {
        previousOrientLandscape = _requestedOrientLandscape.value!!
        _requestedOrientLandscape.value = true
    }

    fun setOrientPortrait() {
        previousOrientLandscape = _requestedOrientLandscape.value!!
        _requestedOrientLandscape.value = false
    }

    fun setPreviousOrientation() {
//        previousOrientLandscape = false
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

    fun getMovieTrailers(): List<String> {
        val moviesData = ContentManager.getDatabaseData()
        return moviesData.map { it.trailerUrl }
    }

    fun getMovieTrailersCardsData(): List<Pair<Int, Int>> {
        val moviesData = ContentManager.getDatabaseData()
        val trailerImagesList = moviesData.map { it.trailerImage }
        val titlesAbbrevsList = moviesData.map { it.titleAbbrev }
        return trailerImagesList.zip(titlesAbbrevsList)
    }

    fun selectedMainScreenTabIndex(): Int {
        return _selectedMainScreenTabIndex!!
    }

    fun selectedMainScreenTabIndex(newTabIndex: Int) {
        _selectedMainScreenTabIndex = newTabIndex
    }

    fun isExpandedTrailerCard(): Boolean {
        return _isExpandedTrailerCard!!
    }

    fun isExpandedTrailerCard(isExpandedNow: Boolean) {
        _isExpandedTrailerCard = isExpandedNow
    }

    fun expandedTrailerCardIndex(): Int {
        return _expandedTrailerCardIndex!!
    }

    fun expandedTrailerCardIndex(newCardIndex: Int) {
        _expandedTrailerCardIndex = newCardIndex
    }

    fun changeOrientToLandscape() {
        previousOrientLandscape = currentOrientLandscape
        currentOrientLandscape = true
        changeOrientationToLandscape()
    }

    fun changeOrientToPrevious() {
        currentOrientLandscape = previousOrientLandscape
        if (currentOrientLandscape != null && currentOrientLandscape!!)
            changeOrientationToLandscape()
        else
            changeOrientationToPortrait()
    }

}
package com.example.movies


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, val player: ExoPlayer) : ViewModel(){
    private var _selectedMainScreenTabIndex = savedStateHandle.get<Int>("_selectedMainScreenTabIndex")
//    private var _isExpandedTrailerCard = savedStateHandle.get<Boolean>("_isExpandedTrailerCard")
//    private var _expandedTrailerCardIndex = savedStateHandle.get<Int>("_ExpandedTrailerCardIndex")
    private var _lastPlayedTrailerPosition = savedStateHandle.get<Long>("_lastPlayedTrailerPosition")
    private var _lastPlayedTrailerOrientation = savedStateHandle.get<Int>("_lastPlayedTrailerOrientation")
    private var _lastPlayedTrailerID = savedStateHandle.get<Int>("_lastPlayedTrailerID")
//    private val videoUris = savedStateHandle.getStateFlow("videoUris", emptyList<String>())
//    private val moviesDatabase = savedStateHandle.getStateFlow("moviesDatabase", emptyList<MovieDetails>())

    //TODO: test
    private val moviesList = mutableListOf<MovieDetails>()
    //TODO: test
//    private val videoUrisList = mutableListOf<String>()

    init {
        player.prepare()
        if (_selectedMainScreenTabIndex == null)
            _selectedMainScreenTabIndex = 0
//        if (_isExpandedTrailerCard == null)
//            _isExpandedTrailerCard = false
//        if (_expandedTrailerCardIndex == null)
//            _expandedTrailerCardIndex = 0
        if (_lastPlayedTrailerPosition == null)
            _lastPlayedTrailerPosition = 0
        if (_lastPlayedTrailerOrientation == null)
            _lastPlayedTrailerOrientation = 0
        if (_lastPlayedTrailerID == null)
            _lastPlayedTrailerID = -1

        loadDatabase()
        loadVideoUris()
    }

    //TODO: test
    private fun loadDatabase() {
        val moviesData = ContentManager.getDatabaseData()
        moviesList.addAll(moviesData)
    }
    //TODO: test
    private fun loadVideoUris() {
        val trailers = moviesList.map { it.trailerUrl }
//        videoUrisList.addAll(trailers)
        for (trailer in trailers){
            player.addMediaItem(MediaItem.fromUri(trailer))
        }
    }

//    private fun loadDatabase() {
//        val moviesData = ContentManager.getDatabaseData()
//
//        for (movie in moviesData){
//            savedStateHandle["moviesDatabase"] = moviesDatabase.value + movie
//        }
//    }
//
//    private fun loadVideoUris() {
//        val moviesData = moviesDatabase.value
//        val trailers = moviesData.map { it.trailerUrl }
//
//        for (trailer in trailers){
//            savedStateHandle["videoUris"] = videoUris.value + trailer
//            player.addMediaItem(MediaItem.fromUri(trailer))
//        }
//    }

    //TODO: test
    fun getMoviesImagesTitles(): List<Pair<Int, Int>> {
//        val moviesData = moviesDatabase.value
//        val imagesList = moviesData.map { it.mainImage }
//        val titlesList = moviesData.map { it.titleAbbrev }
//        return imagesList.zip(titlesList)
        val imagesList = moviesList.map { it.mainImage }
        val titlesList = moviesList.map { it.titleAbbrev }
        return imagesList.zip(titlesList)
    }

    //TODO: test
    fun getMovieDetailsObject(iD: Int): MovieDetails {
//        val moviesData = moviesDatabase.value
//        return moviesData[iD]
        return moviesList[iD]
    }

    //TODO: test
    fun getMovieTrailers(): List<String> {
//        val moviesData = moviesDatabase.value
//        return moviesData.map { it.trailerUrl }
        return moviesList.map { it.trailerUrl }
    }

    //TODO: test
    fun getMovieTrailersCardsData(): List<Pair<Int, Int>> {
//        val moviesData = moviesDatabase.value
//        val trailerImagesList = moviesData.map { it.trailerImage }
//        val titlesAbbrevsList = moviesData.map { it.titleAbbrev }
//        return trailerImagesList.zip(titlesAbbrevsList)
        val trailerImagesList = moviesList.map { it.trailerImage }
        val titlesAbbrevsList = moviesList.map { it.titleAbbrev }
        return trailerImagesList.zip(titlesAbbrevsList)
    }

    fun getSelectedMainScreenTabIndex(): Int {
        return _selectedMainScreenTabIndex!!
    }

    fun setSelectedMainScreenTabIndex(newTabIndex: Int) {
        _selectedMainScreenTabIndex = newTabIndex
    }

//    private var _lastPlayedTrailerPosition = savedStateHandle.get<Long>("_lastPlayedTrailerPosition")
//    private var _lastPlayedTrailerOrientation = savedStateHandle.get<Int>("_lastPlayedTrailerOrientation")
//    private var _lastPlayedTrailerID = savedStateHandle.get<Int>("_lastPlayedTrailerID")

    fun getLastPlayedTrailerPosition(): Long {
        return _lastPlayedTrailerPosition!!
    }
    fun setLastPlayedTrailerPosition(newPosition: Long) {
        _lastPlayedTrailerPosition = newPosition
    }
    fun getLastPlayedTrailerOrientation(): Int{
        return _lastPlayedTrailerOrientation!!
    }
    fun setLastPlayedTrailerOrientation(newOrientation: Int) {
        _lastPlayedTrailerOrientation = newOrientation
    }
    fun getLastPlayedTrailerID(): Int{
        return _lastPlayedTrailerID!!
    }
    fun setLastPlayedTrailerID(newTrailerID: Int){
        _lastPlayedTrailerID = newTrailerID
    }

//    fun isExpandedTrailerCard(): Boolean {
//        return _isExpandedTrailerCard!!
//    }
//
//    fun isExpandedTrailerCard(isExpandedNow: Boolean) {
//        _isExpandedTrailerCard = isExpandedNow
//    }
//
//    fun expandedTrailerCardIndex(): Int {
//        return _expandedTrailerCardIndex!!
//    }
//
//    fun expandedTrailerCardIndex(newCardIndex: Int) {
//        _expandedTrailerCardIndex = newCardIndex
//    }

}
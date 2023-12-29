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
    private var _lastPlayedTrailerPosition = savedStateHandle.get<Long>("_lastPlayedTrailerPosition")
    private var _lastPlayedTrailerOrientation = savedStateHandle.get<Int>("_lastPlayedTrailerOrientation")
    private var _lastPlayedTrailerID = savedStateHandle.get<Int>("_lastPlayedTrailerID")

    private val moviesList = mutableListOf<MovieDetails>()

    init {
        player.prepare()
        if (_selectedMainScreenTabIndex == null)
            _selectedMainScreenTabIndex = 0
        if (_lastPlayedTrailerPosition == null)
            _lastPlayedTrailerPosition = 0
        if (_lastPlayedTrailerOrientation == null)
            _lastPlayedTrailerOrientation = 0
        if (_lastPlayedTrailerID == null)
            _lastPlayedTrailerID = -1

        loadDatabase()
        loadVideoUris()
    }

    private fun loadDatabase() {
        val moviesData = ContentManager.getDatabaseData()
        moviesList.addAll(moviesData)
    }
    private fun loadVideoUris() {
        val trailers = moviesList.map { it.trailerUrl }
        for (trailer in trailers){
            player.addMediaItem(MediaItem.fromUri(trailer))
        }
    }

    fun getMoviesImagesTitles(): List<Pair<Int, Int>> {
        val imagesList = moviesList.map { it.mainImage }
        val titlesList = moviesList.map { it.titleAbbrev }
        return imagesList.zip(titlesList)
    }

    fun getMovieDetailsObject(iD: Int): MovieDetails {
        return moviesList[iD]
    }

    fun getMovieTrailersCardsData(): List<Pair<Int, Int>> {
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
}
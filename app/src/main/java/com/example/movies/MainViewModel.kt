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
    private var _isExpandedTrailerCard = savedStateHandle.get<Boolean>("_isExpandedTrailerCard")
    private var _expandedTrailerCardIndex = savedStateHandle.get<Int>("_ExpandedTrailerCardIndex")
    private val videoUris = savedStateHandle.getStateFlow("videoUris", emptyList<String>())
    private val moviesDatabase = savedStateHandle.getStateFlow("moviesDatabase", emptyList<MovieDetails>())

    init {
        player.prepare()
        if (_selectedMainScreenTabIndex == null)
            _selectedMainScreenTabIndex = 0
        if (_isExpandedTrailerCard == null)
            _isExpandedTrailerCard = false
        if (_expandedTrailerCardIndex == null)
            _expandedTrailerCardIndex = 0

        loadDatabase()
        loadVideoUris()
    }

    private fun loadDatabase() {
        val moviesData = ContentManager.getDatabaseData()

        for (movie in moviesData){
            savedStateHandle["moviesDatabase"] = moviesDatabase.value + movie
        }
    }

    private fun loadVideoUris() {
        val moviesData = moviesDatabase.value
        val trailers = moviesData.map { it.trailerUrl }

        for (trailer in trailers){
            savedStateHandle["videoUris"] = videoUris.value + trailer
            player.addMediaItem(MediaItem.fromUri(trailer))
        }
    }

    fun getMoviesImagesTitles(): List<Pair<Int, Int>> {
        val moviesData = moviesDatabase.value
        val imagesList = moviesData.map { it.mainImage }
        val titlesList = moviesData.map { it.titleAbbrev }
        return imagesList.zip(titlesList)
    }

    fun getMovieDetailsObject(iD: Int): MovieDetails {
        val moviesData = moviesDatabase.value
        return moviesData[iD]
    }

    fun getMovieTrailers(): List<String> {
        val moviesData = moviesDatabase.value
        return moviesData.map { it.trailerUrl }
    }

    fun getMovieTrailersCardsData(): List<Pair<Int, Int>> {
        val moviesData = moviesDatabase.value
        val trailerImagesList = moviesData.map { it.trailerImage }
        val titlesAbbrevsList = moviesData.map { it.titleAbbrev }
        return trailerImagesList.zip(titlesAbbrevsList)
    }

    fun getSelectedMainScreenTabIndex(): Int {
        return _selectedMainScreenTabIndex!!
    }

    fun setSelectedMainScreenTabIndex(newTabIndex: Int) {
        _selectedMainScreenTabIndex = newTabIndex
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
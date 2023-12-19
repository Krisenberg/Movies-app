package com.example.movies

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val application: Application) : ViewModel(){
    private var previousOrientLandscape = savedStateHandle.get<Boolean>("previousOrientLandscape")
    private var currentOrientLandscape = savedStateHandle.get<Boolean>("currentOrientLandscape")
    private val _requestedOrientLandscape = savedStateHandle.getLiveData<Boolean>("_requestedOrientLandscape", false)
    private var _selectedMainScreenTabIndex = savedStateHandle.get<Int>("_selectedMainScreenTabIndex")
    private var _isExpandedTrailerCard = savedStateHandle.get<Boolean>("_isExpandedTrailerCard")
    private var _expandedTrailerCardIndex = savedStateHandle.get<Int>("_ExpandedTrailerCardIndex")
    private var player = savedStateHandle.get<ExoPlayer>("player")

//    private lateinit var player: ExoPlayer
//    private lateinit var playerView: PlayerView
    private lateinit var changeOrientationToLandscape: () -> Unit
    private lateinit var changeOrientationToPortrait: () -> Unit

    private val _backAction = MutableLiveData<() -> Unit>()
    val backAction: LiveData<() -> Unit> = _backAction

    fun performBackAction(action: () -> Unit) {
        _backAction.value = action
    }

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

        if (player == null) {
            player = ExoPlayer.Builder(application.applicationContext).build()
            val movies = getMovieTrailers()
            for (trailerUri in movies) {
                val mediaItem = MediaItem.fromUri(trailerUri)
                player!!.addMediaItem(mediaItem)
            }
        }
    }

//    fun getPlayerView(trailerID: Int): PlayerView {
//        player!!.prepare()
//
//        val playerView = PlayerView(application.applicationContext)
//        playerView.player = player!!
//
////        val playerView = PlayerView(context)
////        playerView.player = player
//        playerView.useController = true
//        playerView.keepScreenOn = true
//        player!!.seekTo(trailerID,0)
//        return playerView
//    }

    fun getPlayer(trailerID: Int): ExoPlayer {
//        val player = ExoPlayer.Builder(application.applicationContext).build()
//        val movies = getMovieTrailers()
//        for (trailerUri in movies) {
//            val mediaItem = MediaItem.fromUri(trailerUri)
//            player!!.addMediaItem(mediaItem)
//        }
//        if (player != null)
//            player!!.release()

        if (player == null) {
            player = ExoPlayer.Builder(application.applicationContext).build()
            val movies = getMovieTrailers()
            for (trailerUri in movies) {
                val mediaItem = MediaItem.fromUri(trailerUri)
                player!!.addMediaItem(mediaItem)
            }
        } else {
            player!!.stop()
        }
        player!!.prepare()
        player!!.seekTo(trailerID,0)
        return player!!
    }

    fun getPlayerView(trailerID: Int): PlayerView {
        player = getPlayer(trailerID)

        val playerView = PlayerView(application.applicationContext)
        playerView.player = player
        playerView.useController = true
        playerView.keepScreenOn = true

        return playerView
    }

    fun stopPlayerView(){
        if (player != null)
            player!!.stop()
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
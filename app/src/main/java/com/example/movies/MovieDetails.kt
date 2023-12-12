package com.example.movies

data class MovieDetails(
    val mainImage: Int,
    val title: Int,
    val titleAbbrev: Int,
    val description: Int,
    val details: List<Int>,
    val scenes: List<Int>,
    val actors: List<Int>,
    val trailerUrl: String,
    val trailerImage: Int
)

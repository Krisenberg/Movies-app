package com.example.movies

data class MovieDetails(
    val mainImage: Int,
    val title: Int,
    val description: String,
    val details: List<String>,
    val scenes: List<Int>,
    val actors: List<String>
)

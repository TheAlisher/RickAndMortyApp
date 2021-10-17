package com.alis.rickandmorty.domain.models.episode

data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: MutableList<String>,
    val url: String,
    val created: String
)
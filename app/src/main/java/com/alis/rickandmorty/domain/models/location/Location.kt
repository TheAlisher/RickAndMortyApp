package com.alis.rickandmorty.domain.models.location

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: MutableList<String>,
    val url: String,
    val created: String
)
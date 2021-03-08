package com.alis.rickandmorty.models.common

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse<T : Any>(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: MutableList<T>
)
package com.alis.rickandmorty.models.character

import com.google.gson.annotations.SerializedName

data class SimpleLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
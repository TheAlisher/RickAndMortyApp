package com.alis.rickandmorty.data.network.dtos.character

import com.alis.rickandmorty.domain.models.character.SimpleLocation
import com.google.gson.annotations.SerializedName

class SimpleLocationDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun SimpleLocationDto.toSimpleLocation(): SimpleLocation {
    return SimpleLocation(
        name,
        url
    )
}
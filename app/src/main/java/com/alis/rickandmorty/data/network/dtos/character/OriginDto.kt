package com.alis.rickandmorty.data.network.dtos.character

import com.alis.rickandmorty.domain.models.character.Origin
import com.google.gson.annotations.SerializedName

class OriginDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun OriginDto.toOrigin(): Origin {
    return Origin(
        name, url
    )
}
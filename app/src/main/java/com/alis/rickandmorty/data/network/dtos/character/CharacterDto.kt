package com.alis.rickandmorty.data.network.dtos.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alis.rickandmorty.domain.models.character.Character
import com.google.gson.annotations.SerializedName

@Entity
class CharacterDto(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: OriginDto,
    @SerializedName("location")
    val location: SimpleLocationDto,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String,
)

fun CharacterDto.toCharacter(): Character {
    return Character(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin.toOrigin(),
        location.toSimpleLocation(),
        image,
        episode,
        url,
        created
    )
}
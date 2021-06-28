package com.alis.rickandmorty.models.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Character(
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
    val origin: Origin,
    @SerializedName("location")
    val location: SimpleLocation,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String,

    var firstSeenIn: String? = null
)
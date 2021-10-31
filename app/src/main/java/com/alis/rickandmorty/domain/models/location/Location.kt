package com.alis.rickandmorty.domain.models.location

import com.alis.rickandmorty.base.IBaseDiffModel

data class Location(
    override val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: MutableList<String>,
    val url: String,
    val created: String
) : IBaseDiffModel<Int>
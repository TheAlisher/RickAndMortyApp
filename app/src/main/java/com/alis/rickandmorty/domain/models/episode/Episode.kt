package com.alis.rickandmorty.domain.models.episode

import com.alis.rickandmorty.common.base.IBaseDiffModel

data class Episode(
    override val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: MutableList<String>,
    val url: String,
    val created: String
) : IBaseDiffModel
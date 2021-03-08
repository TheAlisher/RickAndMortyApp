package com.alis.rickandmorty.data.repositories

import com.alis.rickandmorty.data.network.retrofit.RickAndMortyAPI
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val api: RickAndMortyAPI
) {


}
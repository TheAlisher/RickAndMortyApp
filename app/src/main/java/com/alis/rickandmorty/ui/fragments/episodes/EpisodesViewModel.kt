package com.alis.rickandmorty.ui.fragments.episodes

import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel() {

}
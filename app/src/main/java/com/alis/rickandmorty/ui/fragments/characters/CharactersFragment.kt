package com.alis.rickandmorty.ui.fragments.characters

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragmentWithViewModel
import com.alis.rickandmorty.databinding.FragmentCharactersBinding
import com.alis.rickandmorty.extensions.showToastLong
import com.alis.rickandmorty.ui.adapters.CharacterAdapter
import com.alis.rickandmorty.ui.common.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharactersFragment :
    BaseFragmentWithViewModel<CharactersViewModel, FragmentCharactersBinding>(
        R.layout.fragment_characters
    ) {

    override val viewModel: CharactersViewModel by viewModels()
    override val binding: FragmentCharactersBinding by viewBinding()

    private val characterAdapter = CharacterAdapter(this::onItemClick)
    private val loadStateAdapter = LoadStateAdapter {
        characterAdapter.retry()
    }

    override fun setupViews() {
        binding.recyclerCharacters.apply {
            adapter = characterAdapter.withLoadStateFooter(
                footer = loadStateAdapter
            )
            layoutManager = LinearLayoutManager(context)
        }

        characterAdapter.addLoadStateListener { loadStates ->
            binding.apply {
                progressCharactersLoader.isVisible = loadStates.refresh is LoadState.Loading
                recyclerCharacters.isVisible = loadStates.refresh !is LoadState.Loading
            }
        }
    }

    override fun setupListeners() {

    }

    private fun onItemClick() {
        //TODO
    }

    override fun setupObservers() {
        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                characterAdapter.submitData(it)
            }
        })
    }


    /*viewModel.fetchCharacters().observe(viewLifecycleOwner, {
        when (it.status) {
            Status.LOADING -> {
                showToastLong("LOADING")
            }
            Status.ERROR -> {
                showToastShort(it.message.toString())
            }
            Status.SUCCESS -> {
                characterAdapter.setList(it.data!!.body()!!.results)
                Log.d("anime", viewModel.getCharacters().toString())
            }
        }
    })*/
}
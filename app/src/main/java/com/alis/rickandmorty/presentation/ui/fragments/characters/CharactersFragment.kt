package com.alis.rickandmorty.presentation.ui.fragments.characters

import android.net.Uri
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.common.resource.Resource
import com.alis.rickandmorty.databinding.FragmentCharactersBinding
import com.alis.rickandmorty.presentation.ui.activity.MainActivity
import com.alis.rickandmorty.presentation.ui.adapters.CharacterAdapter
import com.alis.rickandmorty.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharactersFragment : BaseFragment<CharactersViewModel, FragmentCharactersBinding>(
    R.layout.fragment_characters
) {

    override val viewModel: CharactersViewModel by activityViewModels()
    override val binding by viewBinding(FragmentCharactersBinding::bind)

    private val characterAdapter = CharacterAdapter(
        this::onItemClick, this::onItemLongClick, this::fetchFirstSeenIn
    )
    private val loadStateAdapter = LoadStateAdapter {
        characterAdapter.retry()
    }

    override fun initialize() {
        binding.recyclerCharacters.apply {
            adapter = characterAdapter.withLoadStateFooter(
                footer = loadStateAdapter
            )
            layoutManager = LinearLayoutManager(context)
        }

        characterAdapter.addLoadStateListener { loadStates ->
            binding.apply {
                recyclerCharacters.isVisible = loadStates.refresh is LoadState.NotLoading
                progressCharactersLoader.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.recyclerCharacters.smoothScrollToPosition(0)
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToCharactersDetailFragment(
                label = "${getString(R.string.fragment_label_detail_character)} $name",
                id = id
            )
        )
    }

    private fun onItemLongClick(image: String) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToCharactersPhotoDetailDialog(
                image = image
            )
        )
    }

    private fun fetchFirstSeenIn(position: Int, episodeUrl: String) {
        val id = Uri.parse(episodeUrl).lastPathSegment?.toInt()!!
        lifecycleScope.launch {
            viewModel.fetchEpisode(id).collect {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        it.data?.let { episode ->
                            characterAdapter.setFirstSeenIn(position, episode.name)
                        }
                    }
                    is Resource.Error -> {
                    }
                }
            }
        }
    }

    override fun setupObservers() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }
}
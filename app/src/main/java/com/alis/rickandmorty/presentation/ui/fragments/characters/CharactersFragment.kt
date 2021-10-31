package com.alis.rickandmorty.presentation.ui.fragments.characters

import android.net.Uri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.common.extensions.bindUIToLoadState
import com.alis.rickandmorty.common.resource.Resource
import com.alis.rickandmorty.databinding.FragmentCharactersBinding
import com.alis.rickandmorty.domain.models.character.SimpleLocation
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
        this::onItemClick,
        this::onItemLongClick,
        this::fetchFirstSeenIn,
        this::onItemLastKnowLocationClick,
        this::onItemFirstSeenInClick,
    )

    override fun initialize() = with(binding) {
        recyclerCharacters.apply {
            adapter = characterAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { characterAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context)
        }

        characterAdapter.bindUIToLoadState(recyclerCharacters, progressCharactersLoader) {
        }
    }

    override fun setupListeners() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.recyclerCharacters.smoothScrollToPosition(0)
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToCharacterDetailFragment(
                label = "${getString(R.string.fragment_label_detail_character)} $name",
                id = id
            )
        )
    }

    private fun onItemLongClick(image: String) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToCharacterImageDialog(
                image = image
            )
        )
    }

    private fun fetchFirstSeenIn(position: Int, episodeUrl: String) {
        lifecycleScope.launch {
            viewModel.fetchEpisode(episodeUrl.getIdFromUrl()).collect {
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

    private fun onItemLastKnowLocationClick(location: SimpleLocation) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToLocationDetailFragment(
                label = "${getString(R.string.fragment_label_detail_location)} ${location.name}",
                id = location.url.getIdFromUrl()
            )
        )
    }

    private fun onItemFirstSeenInClick(name: String, url: String) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToEpisodeDetailFragment(
                label = "${getString(R.string.fragment_label_detail_episode)} $name",
                id = url.getIdFromUrl()
            )
        )
    }

    override fun setupObservers() {
        subscribeToCharacters()
    }

    private fun subscribeToCharacters() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    private fun String.getIdFromUrl() = Uri.parse(this).lastPathSegment?.toInt()!!
}
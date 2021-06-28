package com.alis.rickandmorty.ui.fragments.characters

import android.view.Menu
import android.view.MenuInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragmentWithMenu
import com.alis.rickandmorty.databinding.FragmentCharactersBinding
import com.alis.rickandmorty.ui.activity.MainActivity
import com.alis.rickandmorty.ui.adapters.CharacterAdapter
import com.alis.rickandmorty.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharactersFragment : BaseFragmentWithMenu<CharactersViewModel, FragmentCharactersBinding>(
    R.layout.fragment_characters
) {

    override val viewModel: CharactersViewModel by viewModels()
    override val binding by viewBinding(FragmentCharactersBinding::bind)

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

    override fun setupObservers() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
    }
}
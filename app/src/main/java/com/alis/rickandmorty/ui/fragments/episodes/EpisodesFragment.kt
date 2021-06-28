package com.alis.rickandmorty.ui.fragments.episodes

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
import com.alis.rickandmorty.databinding.FragmentEpisodesBinding
import com.alis.rickandmorty.ui.activity.MainActivity
import com.alis.rickandmorty.ui.adapters.EpisodeAdapter
import com.alis.rickandmorty.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragmentWithMenu<EpisodesViewModel, FragmentEpisodesBinding>(
    R.layout.fragment_episodes
) {

    override val viewModel: EpisodesViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodesBinding::bind)

    private val episodeAdapter = EpisodeAdapter(this::onItemClick)
    private val loadStateAdapter = LoadStateAdapter {
        episodeAdapter.retry()
    }

    override fun setupViews() {
        binding.recyclerEpisodes.apply {
            adapter = episodeAdapter.withLoadStateFooter(
                footer = loadStateAdapter
            )
            layoutManager = LinearLayoutManager(context)
        }

        episodeAdapter.addLoadStateListener { loadStates ->
            binding.apply {
                recyclerEpisodes.isVisible = loadStates.refresh is LoadState.NotLoading
                progressEpisodesLoader.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.recyclerEpisodes.smoothScrollToPosition(0)
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            EpisodesFragmentDirections.actionNavigationEpisodesToEpisodesDetailFragment(
                label = "${getString(R.string.fragment_label_detail_episode)} $name",
                id = id
            )
        )
    }

    override fun setupObservers() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                episodeAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
    }
}
package com.alis.rickandmorty.presentation.ui.fragments.episodes

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.common.base.BaseFragment
import com.alis.rickandmorty.common.extensions.bindUIToLoadState
import com.alis.rickandmorty.databinding.FragmentEpisodesBinding
import com.alis.rickandmorty.presentation.ui.activity.MainActivity
import com.alis.rickandmorty.presentation.ui.adapters.EpisodeAdapter
import com.alis.rickandmorty.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<EpisodesViewModel, FragmentEpisodesBinding>(
    R.layout.fragment_episodes
) {

    override val viewModel: EpisodesViewModel by activityViewModels()
    override val binding by viewBinding(FragmentEpisodesBinding::bind)

    private val episodeAdapter = EpisodeAdapter(this::onItemClick)

    override fun initialize() = with(binding) {
        recyclerEpisodes.apply {
            adapter = episodeAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { episodeAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context)
        }

        episodeAdapter.bindUIToLoadState(recyclerEpisodes, progressEpisodesLoader) {
        }
    }

    override fun setupListeners() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.recyclerEpisodes.smoothScrollToPosition(0)
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            EpisodesFragmentDirections.actionNavigationEpisodesToEpisodeDetailFragment(
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
}
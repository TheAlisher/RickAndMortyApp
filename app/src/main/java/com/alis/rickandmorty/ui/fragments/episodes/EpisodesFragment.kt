package com.alis.rickandmorty.ui.fragments.episodes

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.data.resource.Resource
import com.alis.rickandmorty.databinding.FragmentEpisodesBinding
import com.alis.rickandmorty.extensions.gone
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.extensions.visible
import com.alis.rickandmorty.ui.activity.MainActivity
import com.alis.rickandmorty.ui.adapters.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<EpisodesViewModel, FragmentEpisodesBinding>(
    R.layout.fragment_episodes
) {

    override val viewModel: EpisodesViewModel by viewModels()
    override val binding: FragmentEpisodesBinding by viewBinding()

    private val episodeAdapter = EpisodeAdapter(this::onItemClick)

    override fun setupViews() {
        binding.recyclerEpisodes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = episodeAdapter
        }
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
        swipeRefreshListener()
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.recyclerEpisodes.smoothScrollToPosition(0)
        }
    }

    private fun onItemClick() {
        //TODO
    }

    override fun setupObservers() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collect {
                binding.apply {
                    when (it) {
                        is Resource.Loading -> {
                            progressEpisodesLoader.visible()
                        }
                        is Resource.Error -> {
                            progressEpisodesLoader.gone()
                            showToastShort(it.message.toString())
                        }
                        is Resource.Success -> {
                            progressEpisodesLoader.gone()
                            episodeAdapter.submitList(it.data?.results!!)
                        }
                    }
                }
            }
        }
    }
}
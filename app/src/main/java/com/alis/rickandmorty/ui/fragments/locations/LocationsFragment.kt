package com.alis.rickandmorty.ui.fragments.locations

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentLocationsBinding
import com.alis.rickandmorty.ui.activity.MainActivity
import com.alis.rickandmorty.ui.adapters.LocationAdapter
import com.alis.rickandmorty.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationsFragment : BaseFragment<LocationsViewModel, FragmentLocationsBinding>(
    R.layout.fragment_locations
) {

    override val viewModel: LocationsViewModel by activityViewModels()
    override val binding by viewBinding(FragmentLocationsBinding::bind)

    private val locationAdapter = LocationAdapter(this::onItemClick)
    private val loadStateAdapter = LoadStateAdapter {
        locationAdapter.retry()
    }

    override fun initialize() {
        binding.recyclerLocations.apply {
            adapter = locationAdapter.withLoadStateFooter(
                footer = loadStateAdapter
            )
            layoutManager = LinearLayoutManager(context)
        }

        locationAdapter.addLoadStateListener { loadStates ->
            binding.apply {
                recyclerLocations.isVisible = loadStates.refresh is LoadState.NotLoading
                progressLocationsLoader.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.recyclerLocations.smoothScrollToPosition(0)
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            LocationsFragmentDirections.actionNavigationLocationsToLocationDetailFragment(
                label = "${getString(R.string.fragment_label_detail_location)} $name",
                id = id
            )
        )
    }

    override fun setupObservers() {
        lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }
}
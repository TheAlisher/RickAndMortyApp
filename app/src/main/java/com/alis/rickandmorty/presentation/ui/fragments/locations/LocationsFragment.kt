package com.alis.rickandmorty.presentation.ui.fragments.locations

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.common.extensions.bindUIToLoadState
import com.alis.rickandmorty.databinding.FragmentLocationsBinding
import com.alis.rickandmorty.presentation.ui.activity.MainActivity
import com.alis.rickandmorty.presentation.ui.adapters.LocationAdapter
import com.alis.rickandmorty.presentation.ui.adapters.paging.LoadStateAdapter
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

    override fun initialize() = with(binding) {
        recyclerLocations.apply {
            adapter = locationAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { locationAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context)
        }

        locationAdapter.bindUIToLoadState(recyclerLocations, progressLocationsLoader) {
        }
    }

    override fun setupListeners() {
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
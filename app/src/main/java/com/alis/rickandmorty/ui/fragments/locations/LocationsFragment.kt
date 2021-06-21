package com.alis.rickandmorty.ui.fragments.locations

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.data.resource.Resource
import com.alis.rickandmorty.databinding.FragmentLocationsBinding
import com.alis.rickandmorty.extensions.gone
import com.alis.rickandmorty.extensions.showToastLong
import com.alis.rickandmorty.extensions.visible
import com.alis.rickandmorty.models.enums.FromWhere
import com.alis.rickandmorty.ui.activity.MainActivity
import com.alis.rickandmorty.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationsFragment : BaseFragment<LocationsViewModel, FragmentLocationsBinding>(
    R.layout.fragment_locations
) {

    override val viewModel: LocationsViewModel by viewModels()
    override val binding: FragmentLocationsBinding by viewBinding()

    private val locationAdapter = LocationAdapter(this::onItemClick)

    override fun setupViews() {
        binding.recyclerLocations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = locationAdapter
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

    private fun onItemClick(id: Int) {
        findNavController().navigate(
            LocationsFragmentDirections.actionGlobalDetailFragment(
                fromWhere = FromWhere.LOCATIONS, id = id
            )
        )
    }

    override fun setupObservers() {
        lifecycleScope.launch {
            viewModel.fetchLocations().collect {
                binding.apply {
                    when (it) {
                        is Resource.Loading -> {
                            progressLocationsLoader.visible()
                        }
                        is Resource.Error -> {
                            progressLocationsLoader.gone()
                            showToastLong(it.message.toString())
                        }
                        is Resource.Success -> {
                            progressLocationsLoader.gone()
                            locationAdapter.submitList(it.data!!.body()!!.results)
                        }
                    }
                }
            }
        }
    }
}
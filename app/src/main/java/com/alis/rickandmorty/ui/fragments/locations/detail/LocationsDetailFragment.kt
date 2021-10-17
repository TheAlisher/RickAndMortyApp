package com.alis.rickandmorty.ui.fragments.locations.detail

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.data.resource.Resource
import com.alis.rickandmorty.databinding.FragmentLocationsDetailBinding
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.domain.models.location.Location
import com.alis.rickandmorty.ui.fragments.locations.LocationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationDetailFragment : BaseFragment<LocationsViewModel, FragmentLocationsDetailBinding>(
    R.layout.fragment_locations_detail
) {

    override val viewModel: LocationsViewModel by activityViewModels()
    override val binding by viewBinding(FragmentLocationsDetailBinding::bind)
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun setupRequests() {
        lifecycleScope.launch {
            viewModel.fetchLocation(args.id).collect {
                binding.progressBarLocationsDetail.isVisible = it is Resource.Loading
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        it.data?.let { data -> subscribeToLocationsSuccess(data) }
                    }
                    is Resource.Error -> {
                        it.message?.let { message -> showToastShort(message) }
                    }
                }
            }
        }
    }

    private fun subscribeToLocationsSuccess(data: Location) {
        //TODO
    }
}
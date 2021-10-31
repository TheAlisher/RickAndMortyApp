package com.alis.rickandmorty.presentation.ui.fragments.locations.detail

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.common.extensions.showToastShort
import com.alis.rickandmorty.databinding.FragmentLocationDetailBinding
import com.alis.rickandmorty.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment : BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding>(
    R.layout.fragment_location_detail
) {

    override val viewModel: LocationDetailViewModel by activityViewModels()
    override val binding by viewBinding(FragmentLocationDetailBinding::bind)
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun setupRequests() {
        viewModel.fetchLocation(args.id)
    }

    override fun setupObservers() {
        viewModel.locationState.observe(viewLifecycleOwner, {
            binding.progressBarLocationsDetail.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                    showToastShort(it.error)
                }
                is UIState.Success -> {
                    //TODO
                }
            }
        })
    }
}
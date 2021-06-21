package com.alis.rickandmorty.ui.fragments.locations

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.data.network.Status
import com.alis.rickandmorty.databinding.FragmentLocationsBinding
import com.alis.rickandmorty.extensions.showToastLong
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint

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

    }

    private fun onItemClick() {
        //TODO
    }

    override fun setupObservers() {
        viewModel.fetchLocations().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    showToastLong("LOADING")
                }
                Status.ERROR -> {
                    showToastShort(it.message.toString())
                }
                Status.SUCCESS -> {
                    locationAdapter.setList(it.data!!.body()!!.results)
                }
            }
        })
    }
}
package com.alis.rickandmorty.ui.fragments.characters

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.data.network.Status
import com.alis.rickandmorty.databinding.FragmentCharactersBinding
import com.alis.rickandmorty.extensions.showToastLong
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<CharactersViewModel, FragmentCharactersBinding>(
    R.layout.fragment_characters
) {

    override val viewModel: CharactersViewModel by viewModels()
    override val binding: FragmentCharactersBinding by viewBinding()

    private val characterAdapter = CharacterAdapter(this::onItemClick)

    override fun setupViews() {
        binding.recyclerCharacters.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }
    }

    override fun setupListeners() {

    }

    private fun onItemClick() {
        //TODO
    }

    override fun setupObservers() {
        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    showToastLong("LOADING")
                }
                Status.ERROR -> {
                    showToastShort(it.message.toString())
                }
                Status.SUCCESS -> {
                    characterAdapter.setList(it.data!!.body()!!.results)
                    Log.d("anime", viewModel.getCharacters().toString())
                }
            }
        })
    }
}
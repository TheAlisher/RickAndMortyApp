package com.alis.rickandmorty.presentation.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseDiffUtilItemCallback
import com.alis.rickandmorty.databinding.ItemCharacterBinding
import com.alis.rickandmorty.domain.models.character.Character
import com.alis.rickandmorty.presentation.enums.CharacterStatus

class CharacterAdapter(
    val onItemClick: (name: String, id: Int) -> Unit,
    val onItemLongClick: (image: String) -> Unit,
    val fetchFirstSeenIn: (position: Int, episodeUrl: String) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(
    BaseDiffUtilItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    fun setFirstSeenIn(position: Int, firstSeenIn: String) {
        getItem(position)?.firstSeenIn = firstSeenIn
        notifyItemChanged(position)
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {

                root.setOnClickListener {
                    with(getItem(absoluteAdapterPosition)!!) {
                        onItemClick(name, id)
                    }
                }

                root.setOnLongClickListener {
                    onItemLongClick(getItem(absoluteAdapterPosition)!!.image)
                    true
                }

                textItemCharacterLastKnownLocationData.setOnClickListener {
                    Log.d("anime", "textItemCharacterLastKnownLocationData")
                    //TODO
                }

                textItemCharacterFirstSeenInData.setOnClickListener {
                    Log.d("anime", "textItemCharacterFirstSeenInData")
                    //TODO
                }
            }
        }

        fun onBind(character: Character) = with(binding) {
            imageItemCharacter.load(character.image)
            textItemCharacterName.text = character.name
            setupCharacterStatus(character.status)

            textItemCharacterStatusAndSpecies.text = textItemCharacterStatusAndSpecies
                .context
                .resources
                .getString(
                    R.string.hyphen, character.status, character.species
                )
            textItemCharacterLastKnownLocationData.text = character.location.name

            setupFirstSeenIn(character.firstSeenIn, character.episode.first())
        }

        private fun setupCharacterStatus(status: String) = with(binding) {
            when (status) {
                CharacterStatus.ALIVE.status -> {
                    imageItemCharacterStatus.setImageResource(CharacterStatus.ALIVE.image)
                }
                CharacterStatus.DEAD.status -> {
                    imageItemCharacterStatus.setImageResource(CharacterStatus.DEAD.image)
                }
                CharacterStatus.UNKNOWN.status -> {
                    imageItemCharacterStatus.setImageResource(CharacterStatus.UNKNOWN.image)
                }
            }
        }

        private fun setupFirstSeenIn(firstSeenIn: String, episode: String) = with(binding) {
            progressBarCharacterFirstSeenIn.isVisible = firstSeenIn.isEmpty()
            textItemCharacterFirstSeenInData.isVisible = firstSeenIn.isNotEmpty()
            if (firstSeenIn.isEmpty()) {
                fetchFirstSeenIn(absoluteAdapterPosition, episode)
            } else {
                textItemCharacterFirstSeenInData.text = firstSeenIn
            }
        }
    }
}
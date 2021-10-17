package com.alis.rickandmorty.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alis.rickandmorty.R
import com.alis.rickandmorty.databinding.ItemCharacterBinding
import com.alis.rickandmorty.domain.models.character.Character

class CharacterAdapter(
    val onItemClick: (name: String, id: Int) -> Unit,
    val onItemLongClick: (image: String) -> Unit,
    val fetchFirstSeenIn: (position: Int, episodeUrl: String) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(
    diffCallback
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
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)!!.apply {
                    onItemClick(name, id)
                }
            }
            itemView.setOnLongClickListener {
                onItemLongClick(getItem(absoluteAdapterPosition)!!.image)
                true
            }

            binding.apply {

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

        fun onBind(character: Character) {
            binding.apply {
                imageItemCharacter.load(character.image)
                textItemCharacterName.text = character.name
                when (character.status) {
                    "Alive" -> {
                        imageItemCharacterStatus.setImageResource(R.drawable.character_status_alive)
                    }
                    "Dead" -> {
                        imageItemCharacterStatus.setImageResource(R.drawable.character_status_dead)
                    }
                    "unknown" -> {
                        imageItemCharacterStatus.setImageResource(R.drawable.character_status_unknown)
                    }
                }
                textItemCharacterStatusAndSpecies.text =
                    textItemCharacterStatusAndSpecies.context.resources.getString(
                        R.string.hyphen, character.status, character.species
                    )
                textItemCharacterLastKnownLocationData.text = character.location.name

                character.apply {
                    val firstSeenInIsEmpty = firstSeenIn == null
                    progressBarCharacterFirstSeenIn.isVisible = firstSeenInIsEmpty
                    textItemCharacterFirstSeenInData.isVisible = !firstSeenInIsEmpty
                    if (firstSeenInIsEmpty) {
                        fetchFirstSeenIn(absoluteAdapterPosition, episode.first())
                    } else {
                        textItemCharacterFirstSeenInData.text = firstSeenIn
                    }
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}
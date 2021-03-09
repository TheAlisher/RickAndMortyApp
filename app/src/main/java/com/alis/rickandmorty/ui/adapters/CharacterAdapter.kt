package com.alis.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alis.rickandmorty.R
import com.alis.rickandmorty.databinding.ItemCharacterBinding
import com.alis.rickandmorty.models.character.Character

class CharacterAdapter(
    val onItemClick: () -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var list = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            onItemClick()
        }
    }

    override fun getItemCount(): Int = list.size

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

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
                textItemCharacterFirstSeenInData.text = "TODO" //TODO
            }
        }
    }

    fun setList(list: MutableList<Character>) {
        this.list = list
        notifyDataSetChanged()
    }
}
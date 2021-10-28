package com.alis.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alis.rickandmorty.databinding.ItemEpisodeBinding
import com.alis.rickandmorty.domain.models.episode.Episode

class EpisodeAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : PagingDataAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(EpisodeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)!!.apply {
                    onItemClick(name, id)
                }
            }
        }

        fun onBind(episode: Episode) {
            binding.apply {
                binding.apply {
                    textItemEpisodeName.text = episode.name
                    textItemEpisodeAirDate.text = episode.airDate
                    textItemEpisodeCodeOfEpisode.text = episode.episode
                }
            }
        }
    }

    class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }
    }
}
package com.alis.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alis.rickandmorty.common.base.BaseDiffUtilItemCallback
import com.alis.rickandmorty.databinding.ItemEpisodeBinding
import com.alis.rickandmorty.domain.models.episode.Episode

class EpisodeAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : PagingDataAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(
    BaseDiffUtilItemCallback()
) {

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
                with(getItem(absoluteAdapterPosition)!!) {
                    onItemClick(name, id)
                }
            }
        }

        fun onBind(episode: Episode) = with(binding) {
            textItemEpisodeName.text = episode.name
            textItemEpisodeAirDate.text = episode.airDate
            textItemEpisodeCodeOfEpisode.text = episode.episode
        }
    }
}
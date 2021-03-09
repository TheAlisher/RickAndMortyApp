package com.alis.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alis.rickandmorty.databinding.ItemEpisodeBinding
import com.alis.rickandmorty.models.episode.Episode

class EpisodeAdapter(
    val onItemClick: () -> Unit
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private var list = mutableListOf<Episode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            onItemClick()
        }
    }

    override fun getItemCount(): Int = list.size

    inner class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

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

    fun setList(list: MutableList<Episode>) {
        this.list = list
        notifyDataSetChanged()
    }
}
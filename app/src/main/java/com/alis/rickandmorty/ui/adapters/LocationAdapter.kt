package com.alis.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alis.rickandmorty.databinding.ItemLocationBinding
import com.alis.rickandmorty.data.network.dtos.location.LocationDto
import com.alis.rickandmorty.domain.models.location.Location

class LocationAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : PagingDataAdapter<Location, LocationAdapter.LocationViewHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationViewHolder(
        private val binding: ItemLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)!!.apply {
                    onItemClick(name, id)
                }
            }
        }

        fun onBind(location: Location) {
            binding.apply {
                textItemLocationName.text = location.name
                textItemLocationType.text = location.type
                textItemLocationDimension.text = location.dimension
            }
        }
    }

    class LocationDiffCallback : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }
}
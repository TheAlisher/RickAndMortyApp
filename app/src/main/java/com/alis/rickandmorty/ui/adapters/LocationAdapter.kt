package com.alis.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alis.rickandmorty.databinding.ItemLocationBinding
import com.alis.rickandmorty.models.location.Location

class LocationAdapter(
    val onItemClick: () -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private var list = mutableListOf<Location>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            onItemClick()
        }
    }

    override fun getItemCount(): Int = list.size

    inner class LocationViewHolder(
        private val binding: ItemLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(location: Location) {
            binding.apply {
                textItemLocationName.text = location.name
                textItemLocationType.text = location.type
                textItemLocationDimension.text = location.dimension
            }
        }
    }

    fun setList(list: MutableList<Location>) {
        this.list = list
        notifyDataSetChanged()
    }
}
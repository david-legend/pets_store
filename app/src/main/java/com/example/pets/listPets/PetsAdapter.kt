package com.example.pets.listPets

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pets.R
import com.example.pets.database.Pet
import com.example.pets.databinding.ListItemPetBinding

class PetsAdapter(val clickListener: PetListener) : ListAdapter<Pet,
        PetsAdapter.ViewHolder>(PetDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(clickListener, item)
    }

    class ViewHolder private constructor(val binding: ListItemPetBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: PetListener, item: Pet) {
            binding.pet = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPetBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class PetDiffCallback : DiffUtil.ItemCallback<Pet>() {
    override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return oldItem.petId == newItem.petId
    }

    override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return oldItem == newItem
    }
}

class PetListener(val clickListener: (petId: Long) -> Unit) {
    fun onClick(pet: Pet) = clickListener(pet.petId)
}
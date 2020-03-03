package com.example.pets.petDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pets.database.PetDatabaseDao

class PetDetailsViewModelFactory(
    private val petId: Long,
    private val dataSource: PetDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetDetailsViewModel::class.java)) {
            return PetDetailsViewModel(petId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
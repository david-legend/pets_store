package com.example.pets.listPets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pets.database.PetDatabaseDao
import kotlinx.coroutines.Job

class ListPetsViewModel (
    val database: PetDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

//    private var viewModelJob = Job()

    val pets = database.getAllPets()
}
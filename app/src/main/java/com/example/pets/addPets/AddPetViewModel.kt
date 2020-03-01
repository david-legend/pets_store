package com.example.pets.addPets

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pets.database.Pet
import com.example.pets.database.PetDatabaseDao
import kotlinx.coroutines.*

class AddPetViewModel(
    val database: PetDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    lateinit var pet: Pet

    private val _navigateToListPets = MutableLiveData<Boolean>()
    val navigateToListPets: LiveData<Boolean>
        get() = _navigateToListPets

    init {
        _navigateToListPets.value = false
    }

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getValues(name: String, breed: String, age: Int) {
        pet = Pet(name = name, breed = breed, age = age)
    }

    private suspend fun insert(pet: Pet) {
        withContext(Dispatchers.IO) {
            database.insert(pet)
        }
    }

    fun doneEnteringPetInfo() {
        _navigateToListPets.value = true
    }

    fun savePet() {
        uiScope.launch {

            insert(pet)

            // Setting this state variable to true will alert the observer and trigger navigation.
            _navigateToListPets.value = true
        }
    }

}
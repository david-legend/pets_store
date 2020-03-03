package com.example.pets.petDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pets.database.Pet
import com.example.pets.database.PetDatabaseDao
import kotlinx.coroutines.*

class PetDetailsViewModel(
    private val petId: Long = 0L,
    val database: PetDatabaseDao
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _petInfo = MutableLiveData<Pet?>()
    val petInfo: LiveData<Pet?>
        get() = _petInfo

    var pet : Pet? = null

    private suspend fun getPet(petId: Long)  {
        withContext(Dispatchers.IO) {
           _petInfo.value  = database.get(petId)
        }
    }

    fun fetch() {
        uiScope.launch {
            getPet(petId)
        }
    }

}
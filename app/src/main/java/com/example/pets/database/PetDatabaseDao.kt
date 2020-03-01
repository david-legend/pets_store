package com.example.pets.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PetDatabaseDao {

    @Insert
    fun insert(pet: Pet)

    @Update
    fun update(pet: Pet)

    @Query("SELECT * from pets_table WHERE petId = :key")
    fun get(key: Long): Pet?


    @Query("DELETE FROM pets_table")
    fun clear()

    @Query("SELECT * FROM pets_table ORDER BY petId DESC")
    fun getAllPets(): LiveData<List<Pet>>

//    @Query("SELECT * FROM pets_table ORDER BY petId DESC LIMIT 1")
//    fun getLatestPet(): Pet?

    @Query("SELECT * from pets_table WHERE petId = :key")
    fun getPetWithId(key: Long): LiveData<Pet>
}
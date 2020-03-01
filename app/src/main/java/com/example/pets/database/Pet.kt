package com.example.pets.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets_table")
data class Pet(
    @PrimaryKey(autoGenerate = true)
    var petId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String ,

    @ColumnInfo(name = "breed")
    var breed: String ,

    @ColumnInfo(name = "age")
    var age: Int
)
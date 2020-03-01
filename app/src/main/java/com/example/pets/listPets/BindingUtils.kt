package com.example.pets.listPets

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.pets.database.Pet

@BindingAdapter("petName")
fun TextView.setPetName(item: Pet?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("petBreed")
fun TextView.setPetBreed(item: Pet?) {
    item?.let {
        text = item.breed
    }
}

@BindingAdapter("petAge")
fun TextView.setPetAge(item: Pet?) {
    item?.let {
        text = item.age.toString()
    }
}
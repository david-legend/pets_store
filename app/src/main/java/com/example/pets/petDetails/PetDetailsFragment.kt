package com.example.pets.petDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pets.R
import com.example.pets.databinding.FragmentPetDetailsBinding

class PetDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPetDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pet_details, container, false)

        return binding.root
    }
}
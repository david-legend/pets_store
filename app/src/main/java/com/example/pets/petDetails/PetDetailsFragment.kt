package com.example.pets.petDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.pets.R
import com.example.pets.database.PetsDatabase
import com.example.pets.databinding.FragmentPetDetailsBinding

class PetDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPetDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pet_details, container, false)

        val application = requireNotNull(this.activity).application

        var args = PetDetailsFragmentArgs.fromBundle(arguments!!)

        val dataSource = PetsDatabase.getInstance(application).petDatabaseDao
        val viewModelFactory = PetDetailsViewModelFactory(args.selectedPetId, dataSource)

        val petDetailsViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(PetDetailsViewModel::class.java)

        binding.petDetailsViewModel = petDetailsViewModel
        binding.setLifecycleOwner(this)




        return binding.root
    }
}
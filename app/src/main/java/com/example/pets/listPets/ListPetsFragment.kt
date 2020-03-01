package com.example.pets.listPets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pets.R
import com.example.pets.database.PetsDatabase
import com.example.pets.databinding.FragmentListPetsBinding

class ListPetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentListPetsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_pets, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = PetsDatabase.getInstance(application).petDatabaseDao

        val viewModelFactory = ListPetsViewModelFactory(dataSource, application)

        val listPetsViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(ListPetsViewModel::class.java)

        binding.petsListViewModel = listPetsViewModel

        binding.setLifecycleOwner(this)

        val adapter = PetsAdapter(PetListener { petId ->
            Toast.makeText(context, "${petId}", Toast.LENGTH_LONG).show()
        })
        binding.petList.adapter = adapter

        listPetsViewModel.pets.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.addPetsFab.setOnClickListener { it ->
            findNavController().navigate(
                R.id.action_listPetsFragment_to_addPetsFragment
            )
        }
        return binding.root
    }
}
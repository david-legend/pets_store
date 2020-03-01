package com.example.pets.addPets

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pets.R
import com.example.pets.database.PetsDatabase
import com.example.pets.databinding.FragmentAddPetsBinding


class AddPetsFragment : Fragment() {

    lateinit var petName: String
    lateinit var petBreed: String
    var petAge: Int = 0
    lateinit var _addPetViewModel: AddPetViewModel
    lateinit var binding: FragmentAddPetsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_pets, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = PetsDatabase.getInstance(application).petDatabaseDao
        val viewModelFactory = AddPetViewModelFactory(dataSource, application)
        _addPetViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(AddPetViewModel::class.java)

        binding.addPetViewModel = _addPetViewModel

        _addPetViewModel.navigateToListPets.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(R.id.action_addPetsFragment_to_listPetsFragment2)
            }
        })
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_pet, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.save_pet) {
            getPetValues()
            _addPetViewModel.getValues(petName, petBreed, petAge)
            _addPetViewModel.savePet()
            Toast.makeText(activity, "Pet Saved", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    fun getPetValues() {
        petName = binding.name.getText().toString()
        petBreed = binding.breed.getText().toString()
        petAge = binding.age.getText().toString().toInt()
    }
}
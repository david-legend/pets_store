package com.example.pets.listPets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pets.R
import com.example.pets.databinding.FragmentListPetsBinding

class ListPetsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentListPetsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_pets, container, false)

        return binding.root
    }
}
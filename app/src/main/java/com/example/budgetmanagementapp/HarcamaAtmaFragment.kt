package com.example.budgetmanagementapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.budgetmanagementapp.databinding.FragmentHarcamaAtmaBinding
import com.example.budgetmanagementapp.databinding.FragmentSignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class HarcamaAtmaFragment : Fragment() {

    private var _binding: FragmentHarcamaAtmaBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHarcamaAtmaBinding.inflate(inflater, container, false)

        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_harcama_atma, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.buttonHarcamaEkle.setOnClickListener {

        //}
    }

}
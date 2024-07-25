package com.example.budgetmanagementapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.budgetmanagementapp.databinding.FragmentSignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        return binding.root
    // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonKayitOl.setOnClickListener {
            val email =binding.editTextEmailAddress.text.toString()
            val password = binding.editTextPassword2.text.toString()

            if (email.equals("") || password.equals("")){
                Toast.makeText(requireContext(),"Email ve şifre giriniz..", Toast.LENGTH_LONG).show()
            }else{
                auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                    //success
                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                }.addOnFailureListener {
                    Toast.makeText(requireContext(),it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
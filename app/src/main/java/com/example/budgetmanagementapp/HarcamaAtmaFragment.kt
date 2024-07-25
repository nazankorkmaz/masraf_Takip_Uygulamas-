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
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class HarcamaAtmaFragment : Fragment() {

    private var _binding: FragmentHarcamaAtmaBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore : FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHarcamaAtmaBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        firestore = Firebase.firestore
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_harcama_atma, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonHarcamaEkle.setOnClickListener {
            val harcamaTutar = binding.editTextHarcamaTutar.text.toString()
            val harcamaTur = binding.editTextHarcamaTur.text.toString()

            if(auth.currentUser != null){
                val harcamaMap = hashMapOf<String, Any>()
                harcamaMap.put("harcama_tur",harcamaTur)
                harcamaMap.put("userEmail", auth.currentUser!!.email!!)
                harcamaMap.put("harcama_tutar",harcamaTutar)
                harcamaMap.put("date", Timestamp.now())

                firestore.collection("Harcama").add(harcamaMap).addOnSuccessListener {
                    findNavController().navigate(R.id.action_harcamaAtmaFragment_to_homeFragment)

                }.addOnFailureListener {
                    Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
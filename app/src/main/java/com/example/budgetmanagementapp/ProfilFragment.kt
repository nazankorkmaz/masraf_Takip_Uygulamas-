package com.example.budgetmanagementapp

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.budgetmanagementapp.databinding.FragmentProfilBinding
import com.example.budgetmanagementapp.databinding.FragmentSignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return binding.root
    // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSignOut()



        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.icon_home -> {
                    findNavController().navigate(R.id.action_profilFragment_to_homeFragment)
                    true
                }
                else -> false
            }
        }
        val email = getUserEmail()
        binding.textViewMail.text = email

    }

    private fun getUserEmail(): String? {
        return sharedPreferences.getString("user_email", null)
    }

    private fun setupSignOut() {
        binding.imageViewCikis.setOnClickListener {
            showSignOutDialog()
            //auth.signOut()
            //findNavController().navigate(R.id.action_profilFragment_to_loginFragment)
        }
    }

    private fun showSignOutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Çıkış Yap")
        builder.setMessage("Beni unut?")

        builder.setPositiveButton("Evet") { dialog, which ->
            clearUserEmail()
            auth.signOut()
            findNavController().navigate(R.id.action_profilFragment_to_loginFragment)
        }

        builder.setNegativeButton("Hayır") { dialog, which ->
            auth.signOut()
            findNavController().navigate(R.id.action_profilFragment_to_loginFragment)
        }

        builder.show()
    }

    private fun clearUserEmail() {
        val editor = sharedPreferences.edit()
        editor.remove("user_email")
        editor.apply()
    }

}
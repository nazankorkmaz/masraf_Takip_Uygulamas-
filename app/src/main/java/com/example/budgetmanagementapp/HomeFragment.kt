package com.example.budgetmanagementapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetmanagementapp.adapter.HarcamaRecyclerAdapter
import com.example.budgetmanagementapp.databinding.FragmentHomeBinding
import com.example.budgetmanagementapp.model.Harcama
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var harcamaArrayList: ArrayList<Harcama>
    private lateinit var harcamaRecyclerAdapter: HarcamaRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        //harcamaArrayList = ArrayList()
        //harcamaRecyclerAdapter = HarcamaRecyclerAdapter(harcamaArrayList) // Adapter'i burada başlatıyoruz

        harcamaArrayList = ArrayList()
        harcamaRecyclerAdapter = HarcamaRecyclerAdapter(harcamaArrayList)
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter= harcamaRecyclerAdapter
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.icon_profile -> {
                    findNavController().navigate(R.id.action_homeFragment_to_profilFragment)
                    true
                }

                else -> false
            }
        }

        binding.imageViewEkle.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_harcamaAtmaFragment)
        }


        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email

        db.collection("Harcama").whereEqualTo("userEmail", currentUserEmail).orderBy("date",
            Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {
                        val documents = value.documents

                        harcamaArrayList.clear()

                        for (document in documents) {
                            val harcama_tur = document.get("harcama_tur") as String
                            val harcama_tutar = document.get("harcama_tutar") as String
                            val userEmail = document.get("userEmail") as String

                            val harcama = Harcama(harcama_tur,harcama_tutar,userEmail)
                            harcamaArrayList.add(harcama)
                            println("harcama tur : ${harcama_tur}")
                            println("harcama tutar : ${harcama_tutar}")
                        }
                        harcamaRecyclerAdapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }


}
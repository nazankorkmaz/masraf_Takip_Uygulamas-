package com.example.budgetmanagementapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetmanagementapp.databinding.CardHarcamaBinding
import com.example.budgetmanagementapp.model.Harcama

class HarcamaRecyclerAdapter(private val harcamapostList: ArrayList<Harcama>) : RecyclerView.Adapter<HarcamaRecyclerAdapter.HarcamaHomepostHolder>()  {

    class HarcamaHomepostHolder(val binding: CardHarcamaBinding) : RecyclerView.ViewHolder(binding.root)
        //(val binding: FragmentHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarcamaHomepostHolder {
        val binding = CardHarcamaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  HarcamaHomepostHolder(binding)
    }

    override fun getItemCount(): Int {
        println(harcamapostList.size)
        return harcamapostList.size
    }

    override fun onBindViewHolder(holder: HarcamaHomepostHolder, position: Int) {
        val harcama = harcamapostList[position]
        holder.binding.textViewHarcamaTuru.text = harcama.harcama_tur
        holder.binding.textViewHarcamaTutar2.text = harcama.harcama_tutar
    }
}
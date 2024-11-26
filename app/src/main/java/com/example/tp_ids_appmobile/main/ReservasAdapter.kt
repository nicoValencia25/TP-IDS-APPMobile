package com.example.tp_ids_appmobile.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R

class ReservasAdapter(var reservs: List<reserv>) : RecyclerView.Adapter<ReservasViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reservas, parent, false)
        return ReservasViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ReservasViewHolder,
        position: Int
    ) {
        holder.render(reservs[position])
    }

    override fun getItemCount() = reservs.size


}
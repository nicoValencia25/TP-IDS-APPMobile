package com.example.tp_ids_appmobile.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.databinding.ActivityReservasBinding
import com.example.tp_ids_appmobile.R




class ReservasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHotelNameReserv: TextView = view.findViewById(R.id.tvHotelNameReserv)
    private val tvHotelIDReserv: TextView = view.findViewById(R.id.tvHotelIDReserv)

    fun render(reserv: reserv){
        tvHotelNameReserv.text = reserv.name
        tvHotelIDReserv.id = reserv.id
    }
}
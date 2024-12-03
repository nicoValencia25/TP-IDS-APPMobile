package com.example.tp_ids_appmobile.main.view_holder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.main.type.Hotel

class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHotelName: TextView = view.findViewById(R.id.tvHotelName)
    private val tvHotelDescription: TextView = view.findViewById(R.id.tvHotelDescription)

    fun render(hotel: Hotel) {
        tvHotelName.text = hotel.nombre
        // TODO poner la imagen
        tvHotelDescription.text = hotel.descripcion
    }
}
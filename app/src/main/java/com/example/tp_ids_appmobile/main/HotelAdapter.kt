package com.example.tp_ids_appmobile.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
class HotelAdapter(var hotels: List<Hotel>) : RecyclerView.Adapter<HotelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun getItemCount() = hotels.size

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.render(hotels[position])
    }
}
package com.example.tp_ids_appmobile.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R




    class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvServiceName: TextView = view.findViewById(R.id.tvServiceName)


        fun render(service: service){
            tvServiceName.text = service.name

        }
    }

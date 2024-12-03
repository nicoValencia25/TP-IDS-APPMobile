package com.example.tp_ids_appmobile.main.view_holder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.main.type.Service

class HireServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvServiceName: TextView = view.findViewById(R.id.tvServiceName)

    private val btnHireService: Button = view.findViewById(R.id.btnHireService)

    fun render(service: Service, onItemSelected: (Int) -> Unit) {
        tvServiceName.text = service.nombre

        btnHireService.setOnClickListener {
            onItemSelected(layoutPosition)
        }
    }
}
package com.example.tp_ids_appmobile.main.view_holder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.main.type.Service

class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvServiceName: TextView = view.findViewById(R.id.tvServiceName)
    private val btnDeleteService: Button = view.findViewById(R.id.btnDeleteService)

    fun render(service: Service, onItemSelect: (Int) -> Unit) {
        tvServiceName.text = service.nombre

        btnDeleteService.setOnClickListener {
            onItemSelect(layoutPosition)
        }
    }
}

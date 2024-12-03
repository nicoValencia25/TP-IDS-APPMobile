package com.example.tp_ids_appmobile.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.main.type.Service
import com.example.tp_ids_appmobile.main.view_holder.HireServiceViewHolder

class HireServiceAdapter(
    private var service: List<Service>, private val hireService: (Int) -> Unit
) : RecyclerView.Adapter<HireServiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HireServiceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hire_service, parent, false)
        return HireServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: HireServiceViewHolder, position: Int) {
        holder.render(service[position], hireService)
    }

    override fun getItemCount() = service.size
}
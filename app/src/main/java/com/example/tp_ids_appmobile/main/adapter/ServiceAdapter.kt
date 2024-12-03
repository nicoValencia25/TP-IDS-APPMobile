package com.example.tp_ids_appmobile.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.main.type.Service
import com.example.tp_ids_appmobile.main.view_holder.ServiceViewHolder

class ServiceAdapter(
    private var service: List<Service>, private val onItemSelect: (Int) -> Unit
) : RecyclerView.Adapter<ServiceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ServiceViewHolder, position: Int
    ) {
        holder.render(service[position], onItemSelect)
    }

    override fun getItemCount() = service.size


    fun updateServices(newServices: List<Service>) {
        service = newServices
        notifyDataSetChanged()
    }
}
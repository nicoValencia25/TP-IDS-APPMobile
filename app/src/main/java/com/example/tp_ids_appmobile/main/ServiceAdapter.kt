package com.example.tp_ids_appmobile.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R



class ServiceAdapter(var service: List<service>) : RecyclerView.Adapter<ServiceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ServiceViewHolder,
        position: Int
    ) {
        holder.render(service[position])
    }

    override fun getItemCount() = service.size


}
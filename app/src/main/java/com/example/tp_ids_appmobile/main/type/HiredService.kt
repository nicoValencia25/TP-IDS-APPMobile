package com.example.tp_ids_appmobile.main.type

import com.example.tp_ids_appmobile.main.response.HiredServiceDataResponse
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

class HiredService(hiredService: HiredServiceDataResponse) : Serializable {
    var servicioContratadoID: Int = hiredService.servicioContratadoID ?: 1
    var creacion: Long = toDate(hiredService.creacion)
    var precioTotal: Int = hiredService.precioTotal
    var servicioID: Int = hiredService.servicioID
    var reservaID: Int = hiredService.reservaID

    private fun toDate(date: String?): Long {
        date ?: return System.currentTimeMillis()
        val format = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.getDefault())
        return format.parse(date)!!.time
    }
}
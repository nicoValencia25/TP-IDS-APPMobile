package com.example.tp_ids_appmobile.main.type

import com.example.tp_ids_appmobile.main.response.ReserveDataResponse
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

class Reserve(reserve: ReserveDataResponse) : Serializable {
    var reservaID: Int = reserve.reservaID
    var creacion: Long = toDate(reserve.creacion)
    var desde: Long = toDate(reserve.desde)
    var hasta: Long = toDate(reserve.hasta)
    var cantNinos: Int = reserve.cantNinos
    var cantAdultos: Int = reserve.cantAdultos
    var precioTotal: Int = reserve.precioTotal
    var habitacionID: Int = reserve.habitacionID
    var usuarioID: Int = reserve.usuarioID

    private fun toDate(date: String?): Long {
        date ?: return System.currentTimeMillis()
        val format = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.getDefault())
        return format.parse(date)!!.time
    }
}
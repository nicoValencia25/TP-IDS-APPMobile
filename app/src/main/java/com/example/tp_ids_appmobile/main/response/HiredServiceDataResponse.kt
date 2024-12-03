package com.example.tp_ids_appmobile.main.response

import com.google.gson.annotations.SerializedName

class HiredServiceDataResponse(
    @SerializedName("ServicioContratadoID") val servicioContratadoID: Int?,
    @SerializedName("Creacion") val creacion: String?,
    @SerializedName("PrecioTotal") val precioTotal: Int,
    @SerializedName("ServicioID") val servicioID: Int,
    @SerializedName("ReservaID") val reservaID: Int
)
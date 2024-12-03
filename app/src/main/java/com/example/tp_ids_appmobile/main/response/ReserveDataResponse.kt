package com.example.tp_ids_appmobile.main.response

import com.google.gson.annotations.SerializedName

data class ReserveDataResponse(
    @SerializedName("ReservaID") val reservaID: Int,
    @SerializedName("Creacion") val creacion: String?,
    @SerializedName("Desde") val desde: String?,
    @SerializedName("Hasta") val hasta: String?,
    @SerializedName("CantNiños") val cantNinos: Int,
    @SerializedName("CantAdultos") val cantAdultos: Int,
    @SerializedName("PrecioTotal") val precioTotal: Int,
    @SerializedName("HabitacionID") val habitacionID: Int,
    @SerializedName("UsuarioID") val usuarioID: Int
)
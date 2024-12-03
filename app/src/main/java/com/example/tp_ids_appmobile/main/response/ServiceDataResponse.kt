package com.example.tp_ids_appmobile.main.response

import com.google.gson.annotations.SerializedName

data class ServiceDataResponse(
    @SerializedName("ServicioID") val servicioID: Int,
    @SerializedName("Nombre") val nombre: String,
    @SerializedName("Descripcion") val descripcion: String,
    @SerializedName("Precio") val precio: Int,
    @SerializedName("ImgServicio") val imgServicio: String,
    @SerializedName("HotelID") val hotelID: Int
)
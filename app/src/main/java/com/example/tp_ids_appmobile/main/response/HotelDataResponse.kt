package com.example.tp_ids_appmobile.main.response

import com.google.gson.annotations.SerializedName

data class HotelDataResponse(
    @SerializedName("HotelID") val hotelID: Int,
    @SerializedName("Nombre") val nombre: String,
    @SerializedName("Descripcion") val descripcion: String,
    @SerializedName("Provincia") val provincia: String,
    @SerializedName("Direccion") val direccion: String,
    @SerializedName("CodigoPostal") val codigoPostal: Int,
    @SerializedName("Localidad") val localidad: String,
    @SerializedName("Latitud") val latitud: String,
    @SerializedName("Longuitud") val longuitud: String
)
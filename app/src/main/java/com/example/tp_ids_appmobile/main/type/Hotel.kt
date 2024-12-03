package com.example.tp_ids_appmobile.main.type

import com.example.tp_ids_appmobile.main.response.HotelDataResponse
import java.io.Serializable

class Hotel(hotel: HotelDataResponse): Serializable {
    val hotelID: Int = hotel.hotelID
    val nombre: String = hotel.nombre
    val descripcion: String = hotel.descripcion
    val provincia: String = hotel.provincia
    val direccion: String = hotel.direccion
    val codigoPostal: Int = hotel.codigoPostal
    val localidad: String = hotel.localidad
    val latitud: String = hotel.latitud
    val longuitud: String = hotel.longuitud
}
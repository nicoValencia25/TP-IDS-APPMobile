package com.example.tp_ids_appmobile.main.type

import com.example.tp_ids_appmobile.main.response.ServiceDataResponse
import java.io.Serializable

class Service(service: ServiceDataResponse) : Serializable {
    var servicioID: Int = service.servicioID
    var nombre: String = service.nombre
    var descripcion: String = service.descripcion
    var precio: Int = service.precio
    var imgServicio: String = service.imgServicio
    var hotelID: Int = service.hotelID
    var contratado: Boolean = false
}
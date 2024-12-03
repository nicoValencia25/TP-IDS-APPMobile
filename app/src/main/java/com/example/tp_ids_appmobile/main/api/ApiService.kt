package com.example.tp_ids_appmobile.main.api

import com.example.tp_ids_appmobile.main.response.HiredServiceDataResponse
import com.example.tp_ids_appmobile.main.response.HotelDataResponse
import com.example.tp_ids_appmobile.main.response.ReserveDataResponse
import com.example.tp_ids_appmobile.main.response.ServiceDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/api/v1/reservas/{ReserveID}/{Surname}")
    suspend fun getReserveByIDAndSurname(
        @Path("Surname") surname: String, @Path("ReserveID") reserveID: Int
    ): Response<ReserveDataResponse>

    @GET("/api/v1/hoteles")
    suspend fun getHotels(): Response<List<HotelDataResponse>>

    @GET("api/v1/hoteles/habitacion/{HabitationID}")
    suspend fun getHotelByHabitationID(
        @Path("HabitationID") habitationID: Int
    ): Response<HotelDataResponse>

    @GET("api/v1/hoteles/{HotelID}")
    suspend fun getHotelByHotelID(
        @Path("HotelID") hotelID: Int
    ): Response<HotelDataResponse>

    @GET("/api/v1/servicios/hotel/{HotelID}")
    suspend fun getServicesByHotelID(
        @Path("HotelID") hotelID: Int
    ): Response<List<ServiceDataResponse>>

    @GET("/api/v1/servicios_contratados/reserva/{ReserveID}")
    suspend fun getHiredServicesByReserveID(
        @Path("ReserveID") reserveID: Int
    ): Response<List<HiredServiceDataResponse>>

    @DELETE("/api/v1/servicios_contratados/{hiredServiceID}")
    suspend fun removeHiredService(
        @Path("hiredServiceID") hiredServiceID: Int
    ): Response<HiredServiceDataResponse>

    @POST("/api/v1/servicios_contratados")
    suspend fun postHiredService(
        @Body hiredService: HiredServiceDataResponse
    ): Response<HiredServiceDataResponse>
}
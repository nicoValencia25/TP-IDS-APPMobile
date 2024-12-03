package com.example.tp_ids_appmobile.main.activity

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.databinding.ActivityReservasBinding
import com.example.tp_ids_appmobile.main.adapter.HireServiceAdapter
import com.example.tp_ids_appmobile.main.adapter.ServiceAdapter
import com.example.tp_ids_appmobile.main.api.ApiService
import com.example.tp_ids_appmobile.main.response.HiredServiceDataResponse
import com.example.tp_ids_appmobile.main.type.HiredService
import com.example.tp_ids_appmobile.main.type.Hotel
import com.example.tp_ids_appmobile.main.type.Reserve
import com.example.tp_ids_appmobile.main.type.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

class ReservasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservasBinding
    private lateinit var retrofit: Retrofit

    private lateinit var reserve: Reserve
    private lateinit var hotel: Hotel
    private var hotelServices: List<Service> = emptyList()
    private var hiredServices: List<HiredService> = emptyList()

    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var hireServiceAdapter: HireServiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initApi()
    }

    private fun initApi() {
        retrofit = getRetrofit()

        reserve = intent.getSerializableExtra("RESERVE")!! as Reserve

        CoroutineScope(Dispatchers.IO).launch {
            searchHotelByHabitationID(reserve.habitacionID).join()
            //searchHotelByHabitationID(7).join()

            searchServicesByHotelID(hotel.hotelID).join()
            searchHiredServiceByReserveID(reserve.reservaID).join()

            runOnUiThread {
                setServicesHired(hotelServices, hiredServices)
                initUI()
                initListener()
            }
        }
    }

    private fun initListener() {
        binding.fabAddTask.setOnClickListener {
            showDialogHireService()
        }
    }

    private fun initUI() {
        binding.tvHotelNameReserv.text = hotel.nombre
        binding.tvHotelIDReserv.text = reserve.reservaID.toString()

        val servicesHired = hotelServices.filter { it.contratado }

        showServices(servicesHired)

        serviceAdapter = ServiceAdapter(servicesHired) { removeService(it) }
        binding.rvShowService.layoutManager = LinearLayoutManager(this)
        binding.rvShowService.adapter = serviceAdapter
    }

    private fun updateUI() {
        hotelServices = emptyList()
        hiredServices = emptyList()

        CoroutineScope(Dispatchers.IO).launch {
            searchServicesByHotelID(hotel.hotelID).join()
            searchHiredServiceByReserveID(reserve.reservaID).join()

            runOnUiThread {
                setServicesHired(hotelServices, hiredServices)

                val servicesHired = hotelServices.filter { it.contratado }

                showServices(servicesHired)

                serviceAdapter.updateServices(servicesHired)
            }
        }
    }

    private fun showServices(hired: List<Service>) {
        if (hired.isEmpty()) {
            binding.llService.visibility = View.GONE
        } else {
            binding.llService.visibility = View.VISIBLE
        }
    }

    private fun showDialogHireService() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.hire_service_task)

        val rvHireService: RecyclerView = dialog.findViewById(R.id.rvHireService)
        hireServiceAdapter = HireServiceAdapter(hotelServices.filter { !it.contratado }) {
            onItemHired(
                it
            )

            dialog.hide()
        }
        rvHireService.layoutManager = LinearLayoutManager(this)
        rvHireService.adapter = hireServiceAdapter

        dialog.show()
    }

    private fun removeService(position: Int) {
        var id = 0
        val service = hotelServices.filter { it.contratado }
        for (s in hiredServices) {
            if (s.servicioID == service[position].servicioID) {
                id = s.servicioContratadoID
                break
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            removeHireService(id)

            runOnUiThread { updateUI() }
        }
    }

    private fun onItemHired(position: Int) {
        val service = hotelServices.filter { !it.contratado }

        val body = HiredServiceDataResponse(
            0,
            dateToString(System.currentTimeMillis()),
            service[position].precio,
            service[position].servicioID,
            reserve.reservaID
        )

        CoroutineScope(Dispatchers.IO).launch {
            hireService(body).join()

            runOnUiThread { updateUI() }
        }
    }

    private fun dateToString(date: Long): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.format(date)
    }

    private fun setServicesHired(service: List<Service>, hired: List<HiredService>) {
        if (hired.isEmpty()) return

        for (s in service) {
            for (h in hired) {
                if (s.servicioID == h.servicioID) {
                    s.contratado = true
                    break
                }
            }
        }
    }

    private fun searchHotelByHabitationID(habitationID: Int): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val apiResponse =
                retrofit.create(ApiService::class.java).getHotelByHabitationID(habitationID)
                // retrofit.create(ApiService::class.java).getHotelByHotelID(habitationID)

            if (!apiResponse.isSuccessful) return@launch

            val response = apiResponse.body()

            response ?: return@launch

            hotel = Hotel(response)
        }
    }

    private fun searchServicesByHotelID(hotelID: Int): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = retrofit.create(ApiService::class.java).getServicesByHotelID(hotelID)

            if (!apiResponse.isSuccessful) return@launch

            val response = apiResponse.body()

            response ?: return@launch

            hotelServices = List(response.size) { Service(response[it]) }
        }
    }

    private fun searchHiredServiceByReserveID(reserveID: Int): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val apiResponse =
                retrofit.create(ApiService::class.java).getHiredServicesByReserveID(reserveID)

            if (!apiResponse.isSuccessful) return@launch

            val response = apiResponse.body()

            response ?: return@launch

            hiredServices = List(response.size) { HiredService(response[it]) }
        }
    }

    private fun hireService(service: HiredServiceDataResponse): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = retrofit.create(ApiService::class.java).postHiredService(service)

            if (apiResponse.code() == 201) return@launch
        }
    }

    private fun removeHireService(hiredServiceID: Int): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val apiResponse =
                retrofit.create(ApiService::class.java).removeHiredService(hiredServiceID)

            if (!apiResponse.isSuccessful) return@launch

            if (apiResponse.code() == 200) return@launch
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://juanbarbieri.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
package com.example.tp_ids_appmobile.main.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.databinding.ActivityHotelBinding
import com.example.tp_ids_appmobile.main.adapter.HotelAdapter
import com.example.tp_ids_appmobile.main.api.ApiService
import com.example.tp_ids_appmobile.main.type.Hotel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HotelActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityHotelBinding

    private lateinit var hotels: List<Hotel>

    private lateinit var hotelAdapter: HotelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHotelBinding.inflate(layoutInflater)
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

        hotels = emptyList()

        CoroutineScope(Dispatchers.IO).launch {
            runOnUiThread { binding.tvErrorHotels.visibility = View.GONE }

            val jobHotels = getHotels()
            jobHotels.join()

            runOnUiThread { initUI() }
        }
    }

    private fun initUI() {
        if (hotels.isEmpty()) {
            binding.tvErrorHotels.visibility = View.VISIBLE
        }

        hotelAdapter = HotelAdapter(hotels)
        binding.rvHotel.layoutManager = LinearLayoutManager(this)
        binding.rvHotel.adapter = hotelAdapter
    }

    private fun getHotels(): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = retrofit.create(ApiService::class.java).getHotels()

            if (!apiResponse.isSuccessful) {
                return@launch
            }

            val response = apiResponse.body()

            response ?: return@launch

            hotels = List(response.size) { Hotel(response[it]) }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://juanbarbieri.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
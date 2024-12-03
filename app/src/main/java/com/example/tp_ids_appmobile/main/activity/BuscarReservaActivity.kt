package com.example.tp_ids_appmobile.main.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.databinding.ActivityBuscarReservaBinding
import com.example.tp_ids_appmobile.main.api.ApiService
import com.example.tp_ids_appmobile.main.response.ReserveDataResponse
import com.example.tp_ids_appmobile.main.type.Reserve
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuscarReservaActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityBuscarReservaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBuscarReservaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initApi()
        binding.btnLogIn.setOnClickListener { checkButton() }
    }

    private fun initApi() {
        retrofit = getRetrofit()
    }

    private fun checkButton() {
        if (binding.etUser.text.isNullOrEmpty() || binding.etReserv.text.isNullOrEmpty()) {
            return
        }

        searchBySurname(binding.etUser.text.toString(), binding.etReserv.text.toString().toInt())
    }

    private fun navigateToIndexActivity(response: ReserveDataResponse) {
        val intent = Intent(this, ReservasActivity::class.java)
        intent.putExtra("RESERVE", Reserve(response))
        startActivity(intent)
    }

    private fun searchBySurname(surname: String, reserveID: Int) {
        binding.tvErrorLogin.visibility = View.GONE

        CoroutineScope(Dispatchers.IO).launch {
            val apiResponse =
                retrofit.create(ApiService::class.java).getReserveByIDAndSurname(surname, reserveID)

            if (!apiResponse.isSuccessful) {
                runOnUiThread { binding.tvErrorLogin.visibility = View.VISIBLE }
                return@launch
            }

            val response = apiResponse.body()

            if (response == null) {
                runOnUiThread { binding.tvErrorLogin.visibility = View.VISIBLE }
                return@launch
            }

            runOnUiThread {
                navigateToIndexActivity(response)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://juanbarbieri.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
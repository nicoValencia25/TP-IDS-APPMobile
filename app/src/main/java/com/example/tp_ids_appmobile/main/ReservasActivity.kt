package com.example.tp_ids_appmobile.main

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_ids_appmobile.R
import com.example.tp_ids_appmobile.databinding.ActivityReservasBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReservasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservasBinding
    private lateinit var retrofit: Retrofit
    private lateinit var reservs: MutableList<reserv>
    private lateinit var reservasAdapter: ReservasAdapter
    private lateinit var rvMisReservas : RecyclerView
    private lateinit var service: MutableList<service>
    private lateinit var rvShowService: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets }





        initComponent()
        initUI()
    }
private fun initComponent(){
    //parte de api

    reservs = mutableListOf(
        reserv(
            1,
            "hotel 1"
        )
    )
    rvMisReservas = findViewById(R.id.rvMisReservas)


    service = mutableListOf(
        service(
            1,
            "Transporte"
        ),
        service(
            2,
            "Desayuno"
        )
    )
    rvShowService = findViewById(R.id.rvShowService)

}

private fun initUI(){
    reservasAdapter = ReservasAdapter(reservs)
    rvMisReservas.layoutManager = LinearLayoutManager(this)
    rvMisReservas.adapter = reservasAdapter
    serviceAdapter = ServiceAdapter(service)
    rvShowService.layoutManager = LinearLayoutManager(this)
    rvShowService.adapter = serviceAdapter
}




}
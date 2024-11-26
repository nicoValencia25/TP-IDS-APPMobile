package com.example.tp_ids_appmobile.main

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp_ids_appmobile.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_index)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

val btnMisReservas = findViewById<AppCompatButton>(R.id.btnMisReservas)
btnMisReservas.setOnClickListener{navigateToReservasActivity()}

        val btnHotelesIR = findViewById<AppCompatButton>(R.id.btnHotelesIR)
        btnHotelesIR.setOnClickListener{navigateToHotelActivity()}
}

private fun navigateToReservasActivity(){
    val intent = Intent(this, BuscarReservaActivity::class.java)
    startActivity(intent)
}

    private fun navigateToHotelActivity(){
        val intent = Intent(this, HotelActivity::class.java)
        startActivity(intent)
    }


}
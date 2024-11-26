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

class BuscarReservaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLogIn = findViewById<AppCompatButton>(R.id.btnLogIn)
        btnLogIn.setOnClickListener{navigateToIndexActivity()}
        val etUser = findViewById<EditText>(R.id.etUser)
        val etReserv = findViewById<EditText>(R.id.etReserv)
    }

    private fun navigateToIndexActivity(){
        val intent = Intent(this, ReservasActivity::class.java)
        startActivity(intent)
    }
}
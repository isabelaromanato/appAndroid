package com.example.primeiroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular = findViewById<Button>(R.id.button_calcular)

        val edPeso = findViewById<EditText>(R.id.edit_text_peso)

       val edAltura = findViewById<EditText>(R.id.edit_text_altura)

        val textResultado = findViewById<TextView>(R.id.edit_text_view_resultado)

       btnCalcular.setOnClickListener {
            val peso = edPeso.text.toString().toInt()
           val altura = edAltura.text.toString().toDouble()

           val imc = calculaImc(peso, altura)

           textResultado.text = String.format("%.1f", imc)



        }

        //para o appBar aparecer apenas na tela que gostariamos

        supportActionBar!!.hide()

        //para redimencionar para outra tela

        val tvHello = findViewById<TextView>(R.id.et_data)

        tvHello.setOnClickListener {

            val abrirActivityDatePicker = Intent(this, DatePickerActivity::class.java)

            startActivity(abrirActivityDatePicker)
        }

    }
}

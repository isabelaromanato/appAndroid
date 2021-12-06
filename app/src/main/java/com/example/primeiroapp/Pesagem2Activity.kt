package com.example.primeiroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.primeiroapp.utils.getDataAtualBrasil
import java.time.LocalDate

class Pesagem2Activity : AppCompatActivity() {

    lateinit var tvDataPesagem: TextView
    lateinit var etNovoPeso: EditText
    lateinit var spinnerNivel: Spinner
    lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesagem2)
        supportActionBar!!.hide()

        etNovoPeso = findViewById(R.id.et_peso)
        spinnerNivel = findViewById(R.id.spinner_idade)
        btnRegistrar = findViewById(R.id.button_registrar_peso)
        tvDataPesagem = findViewById(R.id.tv_data_pesagem)

        tvDataPesagem.text = getDataAtualBrasil()
        btnRegistrar.setOnClickListener {


        val arquivo = getSharedPreferences("usuario", MODE_PRIVATE)
            val pesagem = arquivo.getString("pesagem",     "")
            val dataPesagem = arquivo.getString("data_pesagem",     "")
            val nivel = arquivo.getString("nivel",     "")

        val editor = arquivo.edit()
        editor.putString("pesagem", "$pesagem;${etNovoPeso.text.toString()}")
        editor.putString("data_pesagem", "$dataPesagem;${LocalDate.now().toString()}")
        editor.putString("nivel", "$nivel;${spinnerNivel.selectedItemPosition.toString()}")
        editor.apply()

            Toast.makeText(this, "Peso registrado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
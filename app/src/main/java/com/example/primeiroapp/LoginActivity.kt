package com.example.primeiroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()

        val criarConta = findViewById<TextView>(R.id.tv_criar_conta)

        criarConta.setOnClickListener {
            val abrirActivityLogin =
            Intent(this, DatePickerActivity::class.java)

            startActivity(abrirActivityLogin)
        }

    }

}




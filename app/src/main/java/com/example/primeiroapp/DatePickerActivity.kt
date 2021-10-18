package com.example.primeiroapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import java.util.*

class DatePickerActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var etData: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        editEmail = findViewById(R.id.edit_text_email)
        editSenha = findViewById(R.id.edit_text_senha)
        editNome = findViewById(R.id.edit_text_nome)
        editProfissao = findViewById(R.id.edit_text_profissao)
        editAltura = findViewById(R.id.edit_text_altura)
        etData = findViewById(R.id.et_data)

        supportActionBar!!.title = "Novo Usuário"
//        supportActionBar!!.subtitle = "Aprendendo a lidar com os menus"
        //Criar um calendário

        val calendario = Calendar.getInstance()

        //Determinar os dados  (dia, mês e ano) do calendário

        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir o componente DatePicker

        val etDataNascimento = findViewById<EditText>(R.id.et_data)

        etDataNascimento.setOnClickListener {
            val dp = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                        etDataNascimento.setText("$_dia/$mes/$ano")
                    }, ano, mes, dia)

            dp.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(validarCampos()){

        }

        return true
    }

    fun validarCampos(): Boolean {

        var valido = true

        if (editEmail.text.isEmpty()) {
            editEmail.error = "O campo 'e-mail' é obrigatório!"
            valido = false
        }

        if (editSenha.text.isEmpty()) {
            editSenha.error = "O campo 'senha' é obrigatório!"
            valido = false
        }
        if (editNome.text.isEmpty()) {
            editNome.error = "O campo 'nome' é obrigatório!"
            valido = false
        }
        if (editProfissao.text.isEmpty()) {
            editProfissao.error = "O campo 'profissão' é obrigatório!"
            valido = false
        }
        if (editAltura.text.isEmpty()) {
            editAltura.error = "O campo 'altura' é obrigatório!"
            valido = false
        }
        if (etData.text.isEmpty()) {
            etData.error = "O campo 'data' é obrigatório!"
            valido = false
        }

        return valido

    }

}














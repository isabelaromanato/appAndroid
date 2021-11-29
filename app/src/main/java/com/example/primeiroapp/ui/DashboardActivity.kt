package com.example.primeiroapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.primeiroapp.Pesagem2Activity
import com.example.primeiroapp.R
import com.example.primeiroapp.utils.calcularIdade
import com.example.primeiroapp.utils.convertBase64toBitmap

class DashboardActivity : AppCompatActivity() {

    //primeiro roda o onCreate e depois declara as variáveis

    lateinit var tvNome: TextView
    lateinit var tvProfissao: TextView
    lateinit var tvImc: TextView
    lateinit var tvNcd: TextView
    lateinit var tvPeso: TextView
    lateinit var tvIdade: TextView
    lateinit var tvAltura: TextView
    lateinit var ivPerfil: ImageView
    lateinit var relativeNovaPesagem: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tvNome = findViewById(R.id.tv_dash_nome)
        tvProfissao = findViewById(R.id.tv_dash_profissao)
        tvImc = findViewById(R.id.tv_dash_imc)
        tvNcd = findViewById(R.id.tv_dash_ncd)
        tvPeso = findViewById(R.id.tv_dash_peso)
        tvIdade = findViewById(R.id.tv_dash_idade)
        tvAltura = findViewById(R.id.tv_dash_altura)
        ivPerfil = findViewById(R.id.iv_dash_foto_perfil)
        relativeNovaPesagem = findViewById(R.id.relative_pesar_agora)

        relativeNovaPesagem.setOnClickListener {
            val intent = Intent(this, Pesagem2Activity:: class.java)
            startActivity(intent)
        }

        carregarDashboard()
    }

    private fun carregarDashboard() {
        val arquivo = getSharedPreferences("usuario", MODE_PRIVATE)

        tvNome.text = arquivo.getString("nome", "")
        tvProfissao.text = arquivo.getString("profissao", "")
        tvAltura.text = arquivo.getFloat("altura", 0.0f).toString()
        tvIdade.text= calcularIdade(arquivo.getString("dataNascimento", "")!!).toString()

        val bitmap = convertBase64toBitmap(arquivo.getString("fotoPerfil", "")!!)
        ivPerfil.setImageBitmap(bitmap)

    }

}
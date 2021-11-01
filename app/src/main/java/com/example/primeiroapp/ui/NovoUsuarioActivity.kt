package com.example.primeiroapp.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.primeiroapp.R
import com.example.primeiroapp.model.Usuario
import com.example.primeiroapp.utils.convertBitmapToBase64
import com.example.primeiroapp.utils.convertStringToLocalDate
import java.time.LocalDate
import java.util.*

const val CODE_IMAGE = 100

class NovoUsuarioActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var etData: EditText
    lateinit var radioF: RadioButton
    lateinit var radioM: RadioButton
    lateinit var tv_TrocarFoto: TextView
    lateinit var ivFotoPerfil: ImageView
    var imageBitmap: Bitmap? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        editEmail = findViewById(R.id.edit_text_email)
        editSenha = findViewById(R.id.edit_text_senha)
        editNome = findViewById(R.id.edit_text_nome)
        editProfissao = findViewById(R.id.edit_text_profissao)
        editAltura = findViewById(R.id.edit_text_altura)
        etData = findViewById(R.id.et_data)
        radioF = findViewById(R.id.radio_feminino)
        radioM = findViewById(R.id.radio_masculino)
        tv_TrocarFoto = findViewById(R.id.tv_trocar_foto)
        ivFotoPerfil = findViewById(R.id.iv_foto_perfil)


        //Abrir a galeria de fotos para escolher uma foto para o perfil


        tv_TrocarFoto.setOnClickListener{
                abrirGaleria()}

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

                        var diaFinal = _dia
                        var mesFinal = _mes + 1

                        var mesString = "$mesFinal"
                        var diaString = "$diaFinal"

                     if(mesFinal < 10 ) {
                        mesString = "0$mesFinal"

                     }

                        if(diaFinal < 10 ){
                            diaString = "0$diaFinal"
                        }

                        etDataNascimento.setText("$diaString/$mesString/$_ano")
                    }, ano, mes, dia)

            dp.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imagem: Intent?) {
        super.onActivityResult(requestCode, resultCode, imagem)

        //Verificar o código do resultado
//        Log.i("xpto", resultCode.toString())

        if (requestCode == CODE_IMAGE && resultCode == -1){

            //Recuperar a imagem do stream

            val fluxoImagem = contentResolver.openInputStream(imagem!!.data!!)

            //Converter os bits em um bitmap

            imageBitmap = BitmapFactory.decodeStream(fluxoImagem)

            //Colocar o BitMap no ImageView

            ivFotoPerfil.setImageBitmap(imageBitmap)

    }
        }

    private fun abrirGaleria() {

        //Abrir a galeria no dispositivo
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"


    //Abrir a Activity responsável por exibir as imagens
    //Esta Activity retornará o conteúdo selecionado para o nosso app

    startActivityForResult(Intent.createChooser(intent, "Escolha uma foto"), CODE_IMAGE)
    
}


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(validarCampos()){

            //Criar o objeto usuario

                val nascimento = convertStringToLocalDate(etData.text.toString())

              val usuario = Usuario(
                  1,
                  editNome.text.toString(),
                  editEmail.text.toString(),
                  editSenha.text.toString(),
                  0,
                  editAltura.text.toString().toDouble(),
                  LocalDate.of(
                      nascimento.year,
                      nascimento.monthValue,
                      nascimento.dayOfMonth
                  ),
                editProfissao.text.toString(),
                if (radioF.isChecked) 'F' else 'M',
                      convertBitmapToBase64(imageBitmap!!)
              )

            //Salvar o registro em um SharedPreferences

            //A intrução abaixo irá criar um arquivo sharedPreferences se não existir
            //Se existir ele será aberto para edição

            val dados = getSharedPreferences("usuario", Context.MODE_PRIVATE)


            //Vamos criar o objeto que permitirá a edição dos dados do arquivo SharedPreferences

            val editor = dados.edit()
            editor.putInt("id", usuario.id)
            editor.putString("nome", usuario.nome)
            editor.putString("email", usuario.email)
            editor.putString("senha", usuario.senha)
            editor.putInt("peso", usuario.peso)
            editor.putFloat("altura", usuario.altura.toFloat())
            editor.putString("dataNascimento", usuario.dataNascimento.toString())
            editor.putString("profissao", usuario.profissao)
            editor.putString("sexo", usuario.sexo.toString())
            editor.putString("fotoPerfil", usuario.fotoPerfil.toString())
            editor.apply()
            
        }
        Toast.makeText(this, "Usuário Cadastrado", Toast.LENGTH_SHORT).show()
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














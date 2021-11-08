package com.example.primeiroapp.utils

import android.content.Context


fun autenticar(email: String,
               senha:String,
               context: Context): Boolean {

val arquivo = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)

 return email == arquivo.getString("email", "") && senha == arquivo.getString("senha", "")
//poderíamos usar um if também mas como só temos duas condições é mais viável ir direto p return

 }

package com.example.primeiroapp.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun convertStringToLocalDate(brazilDate:String): LocalDate{

    val dateFormatterFromBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val localDateFormat = LocalDate.parse(brazilDate, dateFormatterFromBrazil)

return localDateFormat
}

fun calcularIdade(dataNascimento:String): Int{

    //Obter a data atual (hoje)
    val hoje = LocalDate.now()

    //Converter a data de nascimento em um Local Date
    //Extrair a data em um Array

    val nascimentoArray = dataNascimento.split("-").toTypedArray()

    val nascimento = LocalDate.of(nascimentoArray[0].toInt(),
                                  nascimentoArray[1].toInt(),
                                  nascimentoArray[2].toInt())

    //Obter a idade com a classe period
    //between- entre
    val idade = Period.between(nascimento, hoje).years

    return idade

}

fun getDataAtualBrasil():String{

    val hoje = LocalDate.now() //2021-11-29

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val dataBrasil = hoje.format(formatter)

    return dataBrasil

}

fun converterLocalDateEmDataBrasil() {

}



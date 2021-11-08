package com.example.primeiroapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import java.io.ByteArrayOutputStream
import java.util.*

fun convertBitmapToBase64(bitmap: Bitmap) : String {
    val bitmapArray = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.JPEG,100, bitmapArray)

    return Base64.encodeToString(bitmapArray.toByteArray(), Base64.DEFAULT)

}

fun convertBase64toBitmap (base64Image: String): Bitmap {

    //converter o base64 em bytes

    val imagesBytes = Base64.decode(base64Image, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imagesBytes, 0, imagesBytes.size)
}




package com.example.devtestapp.components

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openWhatsAppChat(context: Context, phoneNumber: String, message: String = "") {

    val url = if (message.isEmpty()) {
        "https://wa.me/$phoneNumber"
    } else {
        "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"
    }
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}

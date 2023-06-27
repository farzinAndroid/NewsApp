package com.example.newsapp.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateChanger {

    fun convertDateTime(input: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(input)
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()
        return date?.let { outputFormat.format(it) }
    }

}
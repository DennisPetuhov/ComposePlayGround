package com.example.composeplayground

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewDatePickerViewModel : ViewModel() {


    suspend fun getCurrentDate(): String {
        return withContext(Dispatchers.Default) {
            val currentTimeMillis = System.currentTimeMillis()
            convertTimeMillisToDate(currentTimeMillis)
        }
    }

    suspend fun convertTimeMillisToDate(timeInMillis: Long): String {
        return withContext(Dispatchers.Default) {
            val date = Date(timeInMillis)
            val dateFormat = SimpleDateFormat("EEE MMM yyyy HH:mm", Locale.getDefault())
            dateFormat.format(date)
        }
    }
}
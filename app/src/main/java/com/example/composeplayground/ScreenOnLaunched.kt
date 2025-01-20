package com.example.composeplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun ScreenOnLaunched(modifier: Modifier = Modifier) {
    val viewModel: NewDatePickerViewModel = viewModel()
    MyScreen(
        currentDate = viewModel::getCurrentDate,
        dateFromCalendar = viewModel::convertTimeMillisToDate
    )


}

@Composable
fun MyScreen(
    currentDate: suspend () -> String,
    dateFromCalendar: suspend (Long) -> String,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val dateInput = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = ""))
    }
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(value = dateInput.value, onValueChange = { dateInput.value = it })

            Button(onClick = {
                coroutineScope.launch {

                    dateInput.value = TextFieldValue(currentDate())
                }
            }) { Text(text = "Get current date") }

            Button(onClick = {
                coroutineScope.launch {

                    dateInput.value = TextFieldValue(dateFromCalendar(10023784654))
                }
            }) { Text(text = "Convert 12345765 to date") }
        }
    }

}
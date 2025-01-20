package com.example.composeplayground.playGround

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyComposable() {
    // LaunchedEffect для загрузки данных
    val data: MutableState<String?> = remember { mutableStateOf(null) }
    LaunchedEffect(key1 = Unit) { // key1 = Unit запускает эффект только оодин раз при композиции
        data.value = fetchData()
    }

    // DisposableEffect для установки обработчика события
    var count by remember { mutableStateOf(0) }
    DisposableEffect(key1 = Unit) {
        val listener: () -> Unit = { count++ } // Увеличиваем счетчик при получении события
        // Установка обработчика события
        onEvent(listener)

        // Удаление обработчика при отмене
        onDispose {
            removeEventListener(listener)
        }
    }

    // rememberCoroutineScope для запуска корутины
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {    // Запуск корутины внутри scope
            delay(2000)
            println("Корутина запущена через 2 секунды")
        }

    }

    // LaunchedEffect with key для запуска разных эффектов
    LaunchedEffect(key1 = "key1") {
        delay(1000)
        println("LaunchedEffect с ключом 'key1' выполнен")
    }

    LaunchedEffect(key1 = "key2") {
        delay(3000)
        println("LaunchedEffect с ключом 'key2' выполнен")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = data.value ?: "Loading...")
        Text("Счетчик: $count")
        Button(onClick = { count++ }) {
            Text("Увеличить счетчик")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    MyComposable()
}

// Симуляция загрузки данных
suspend fun  fetchData(): String {
    delay(1000) // Имитация задержки загрузки
    return "Данные загружены!"
}

// Симуляция обработчика события
private fun onEvent(listener: () -> Unit) {
    println("Обработчик события установлен")
    // Здесь можно выполнить логику установки обработчика
}

// Симуляция удаления обработчика события
private fun removeEventListener(listener: () -> Unit) {
    println("Обработчик события удален")
    // Здесь можно выполнить логику удаления обработчика
}
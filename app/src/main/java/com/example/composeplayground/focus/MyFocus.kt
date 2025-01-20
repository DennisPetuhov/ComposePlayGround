package com.example.composeplayground.focus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

//https://composables.com/tutorials/focus-text
@Composable
fun MyFocus(modifier: Modifier = Modifier) {
    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf("") }
    Spacer(modifier = modifier.height(100.dp))
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        TextField(
            modifier = Modifier.focusRequester(focusRequester),
            value = value,
            onValueChange = {
                value = it
            }
        )

        Button(onClick = {
            focusRequester.requestFocus()
        }) {
            Text("Gain focus")
        }
//        MyClearFocus()
//        MyPassFocusNext()
        MySpecifyFocusOrder()
    }
}

@Preview
@Composable
fun MyClearFocus(modifier: Modifier = Modifier) {

    val focusManager = LocalFocusManager.current
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = {
            value = it
        }
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            focusManager.clearFocus()
        }
    }
}

@Composable
fun MyPassFocusNext(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val focusManager = LocalFocusManager.current
        var value by remember { mutableStateOf("") }

        TextField(
            value = value,
            onValueChange = {
                value = it
            },
            singleLine = true,
            keyboardActions = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Next)
            },
        )
        TextField(
            value = value,
            onValueChange = {
                value = it
            },
            singleLine = true,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
            },
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MySpecifyFocusOrder(modifier: Modifier = Modifier) {
    Row {
        val focusManager = LocalFocusManager.current
        Column {
            val (a, b, c) = createRefs()
            TextField(
                modifier = Modifier
                    .focusRequester(a)
                    .focusProperties {
                        next = b
                    },
                value = "",
                onValueChange = {},
            )
            TextField(
                modifier = Modifier
                    .focusRequester(b)
                    .focusProperties {
                        previous = a
                        next = c
                    },
                value = "",
                onValueChange = {},
            )
            TextField(
                modifier = Modifier
                    .focusRequester(c)
                    .focusProperties {
                        previous = b
                    },
                value = "",
                onValueChange = {},
            )
        }
        Column {
            Button(onClick = {
                focusManager.moveFocus(FocusDirection.Previous)
            }) {
                Text("Previous")
            }
            Button(onClick = {
                focusManager.moveFocus(FocusDirection.Next)
            }) {
                Text("Next")
            }
        }
    }
}
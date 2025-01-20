package com.example.composeplayground.alerDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview

@Composable
fun AlertDialogWithIconSample() {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            title = { Text(text = "Title") },
            text = {
                Column {
                    Spacer(modifier = androidx.compose.ui.Modifier.height(150.dp))
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                    Spacer(modifier = androidx.compose.ui.Modifier.height(50.dp))
                    Text(
                        "This area typically contains the supportive text " +
                                "which presents the details regarding the Dialog's purpose."
                    )
                    Spacer(modifier = androidx.compose.ui.Modifier.height(50.dp))
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                    Spacer(modifier = androidx.compose.ui.Modifier.height(150.dp))
                }
            },
            confirmButton = {
                TextButton(onClick = { openDialog.value = false }) { Text("Confirm") }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) { Text("Dismiss") }
            }
        )
    }
}
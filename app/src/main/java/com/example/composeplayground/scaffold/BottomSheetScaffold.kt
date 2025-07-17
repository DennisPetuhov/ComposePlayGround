@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composeplayground.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MyBottomSheetScaffold() {

    val scope = rememberCoroutineScope()
//    val scaffoldState = rememberBottomSheetScaffoldState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = false,
            density = LocalDensity.current,
            initialValue = SheetValue.Expanded,
            confirmValueChange = { it != SheetValue.Expanded },
            skipHiddenState = false
        )
    )
    BottomSheetScaffold(

        sheetContent = {
            Box(
              modifier =   Modifier
                    .fillMaxWidth()
                    .height(128.dp), contentAlignment = Alignment.Center
            ) {
                Text("Swipe up to expand sheet")
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sheet content")
                Spacer(Modifier.height(20.dp))
                Button(onClick = { scope.launch { scaffoldState.bottomSheetState.hide()} }) {
                    Text("Click to collapse sheet")
                }
            }
        },

        scaffoldState = scaffoldState,
//        topBar = { TopAppBar { Text("Bottom sheet scaffold") } },
//        floatingActionButton = {
//            var clickCount by remember { mutableStateOf(0) }
//            FloatingActionButton(
//                onClick = {
//                    // show snackbar as a suspend function
//                    scope.launch {
//                        scaffoldState.snackbarHostState.showSnackbar("Snackbar #${++clickCount}")
//                    }
//                }
//            ) {
//                Icon(Icons.Default.Favorite, contentDescription = "Localized description")
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End,
        sheetPeekHeight = 128.dp
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(100) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
//                        .background(colors[it % colors.size])
                )
            }
        }
    }
}
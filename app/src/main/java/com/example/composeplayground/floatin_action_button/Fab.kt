package com.example.composeplayground.floatin_action_button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview

@Composable
fun FloatingActionButtonSample() {
    FloatingActionButton(
        onClick = { /* do something */ },
    ) {
        Icon(Icons.Filled.Add, "Localized description")
    }
}

@Preview

@Composable
fun SmallFloatingActionButtonSample() {
    SmallFloatingActionButton(
        onClick = { /* do something */ },
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Localized description")
    }
}

@Preview
@Composable
fun LargeFloatingActionButtonSample() {
    LargeFloatingActionButton(
        onClick = { /* do something */ },
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Localized description",
            modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
        )
    }
}

@Preview

@Composable
fun ExtendedFloatingActionButtonTextSample() {
    ExtendedFloatingActionButton(onClick = { /* do something */ }) { Text(text = "Extended FAB") }
}

@Preview

@Composable
fun ExtendedFloatingActionButtonSample() {
    ExtendedFloatingActionButton(
        onClick = { /* do something */ },
        icon = { Icon(Icons.Filled.Add, "Localized description") },
        text = { Text(text = "Extended FAB") },
    )
}


//@Preview
//
//@Composable
//fun MediumFloatingActionButtonSample() {
//    MediumFloatingActionButton(
//        onClick = { /* do something */ },
//    ) {
//        Icon(
//            Icons.Filled.Add,
//            contentDescription = "Localized description",
//            modifier = Modifier.size(FloatingActionButtonDefaults.MediumIconSize),
//        )
//    }
//}


//
//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun AnimatedFloatingActionButtonSample() {
//    val listState = rememberLazyListState()
//    // The FAB is initially shown. Upon scrolling past the first item we hide the FAB by using a
//    // remembered derived state to minimize unnecessary compositions.
//    val fabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
//
//    Scaffold(
//        floatingActionButton = {
//            MediumFloatingActionButton(
//                modifier =
//                Modifier.animateFloatingActionButton(
//                    visible = fabVisible,
//                    alignment = Alignment.BottomEnd
//                ),
//                onClick = { /* do something */ },
//            ) {
//                Icon(
//                    Icons.Filled.Add,
//                    contentDescription = "Localized description",
//                    modifier = Modifier.size(FloatingActionButtonDefaults.MediumIconSize),
//                )
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End,
//    ) {
//        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
//            for (index in 0 until 100) {
//                item { Text(text = "List item - $index", modifier = Modifier.padding(24.dp)) }
//            }
//        }
//    }
//}
//

//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun SmallExtendedFloatingActionButtonTextSample() {
//    SmallExtendedFloatingActionButton(onClick = { /* do something */ }) {
//        Text(text = "Small Extended FAB")
//    }
//}

//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun MediumExtendedFloatingActionButtonTextSample() {
//    MediumExtendedFloatingActionButton(onClick = { /* do something */ }) {
//        Text(text = "Medium Extended FAB")
//    }
//}
//
//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun LargeExtendedFloatingActionButtonTextSample() {
//    LargeExtendedFloatingActionButton(onClick = { /* do something */ }) {
//        Text(text = "Large Extended FAB")
//    }
//}
//

//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun SmallExtendedFloatingActionButtonSample() {
//    SmallExtendedFloatingActionButton(
//        onClick = { /* do something */ },
//        icon = { Icon(Icons.Filled.Add, "Localized description") },
//        text = { Text(text = "Small Extended FAB") },
//    )
//}
//
//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun MediumExtendedFloatingActionButtonSample() {
//    MediumExtendedFloatingActionButton(
//        onClick = { /* do something */ },
//        icon = {
//            Icon(
//                Icons.Filled.Add,
//                "Localized description",
//                modifier = Modifier.size(FloatingActionButtonDefaults.MediumIconSize)
//            )
//        },
//        text = { Text(text = "Medium Extended FAB") },
//    )
//}
//
//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun LargeExtendedFloatingActionButtonSample() {
//    LargeExtendedFloatingActionButton(
//        onClick = { /* do something */ },
//        icon = {
//            Icon(
//                Icons.Filled.Add,
//                "Localized description",
//                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
//            )
//        },
//        text = { Text(text = "Large Extended FAB") },
//    )
//}
//
//@Preview
//@Sampled
//@Composable
//fun AnimatedExtendedFloatingActionButtonSample() {
//    val listState = rememberLazyListState()
//    // The FAB is initially expanded. Once the first visible item is past the first item we
//    // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
//    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
//    Scaffold(
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                onClick = { /* do something */ },
//                expanded = expandedFab,
//                icon = { Icon(Icons.Filled.Add, "Localized Description") },
//                text = { Text(text = "Extended FAB") },
//            )
//        },
//        floatingActionButtonPosition = FabPosition.End,
//    ) {
//        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
//            for (index in 0 until 100) {
//                item { Text(text = "List item - $index", modifier = Modifier.padding(24.dp)) }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun SmallAnimatedExtendedFloatingActionButtonSample() {
//    val listState = rememberLazyListState()
//    // The FAB is initially expanded. Once the first visible item is past the first item we
//    // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
//    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
//    Scaffold(
//        floatingActionButton = {
//            SmallExtendedFloatingActionButton(
//                onClick = { /* do something */ },
//                expanded = expandedFab,
//                icon = { Icon(Icons.Filled.Add, "Localized Description") },
//                text = { Text(text = "Small Extended FAB") },
//            )
//        },
//        floatingActionButtonPosition = FabPosition.End,
//    ) {
//        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
//            for (index in 0 until 100) {
//                item { Text(text = "List item - $index", modifier = Modifier.padding(24.dp)) }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun MediumAnimatedExtendedFloatingActionButtonSample() {
//    val listState = rememberLazyListState()
//    // The FAB is initially expanded. Once the first visible item is past the first item we
//    // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
//    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
//    Scaffold(
//        floatingActionButton = {
//            MediumExtendedFloatingActionButton(
//                onClick = { /* do something */ },
//                expanded = expandedFab,
//                icon = {
//                    Icon(
//                        Icons.Filled.Add,
//                        "Localized Description",
//                        modifier = Modifier.size(FloatingActionButtonDefaults.MediumIconSize)
//                    )
//                },
//                text = { Text(text = "Medium Extended FAB") },
//            )
//        },
//        floatingActionButtonPosition = FabPosition.End,
//    ) {
//        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
//            for (index in 0 until 100) {
//                item { Text(text = "List item - $index", modifier = Modifier.padding(24.dp)) }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Preview
//@Sampled
//@Composable
//fun LargeAnimatedExtendedFloatingActionButtonSample() {
//    val listState = rememberLazyListState()
//    // The FAB is initially expanded. Once the first visible item is past the first item we
//    // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
//    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
//    Scaffold(
//        floatingActionButton = {
//            LargeExtendedFloatingActionButton(
//                onClick = { /* do something */ },
//                expanded = expandedFab,
//                icon = {
//                    Icon(
//                        Icons.Filled.Add,
//                        "Localized Description",
//                        modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
//                    )
//                },
//                text = { Text(text = "Large Extended FAB") },
//            )
//        },
//        floatingActionButtonPosition = FabPosition.End,
//    ) {
//        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
//            for (index in 0 until 100) {
//                item { Text(text = "List item - $index", modifier = Modifier.padding(24.dp)) }
//            }
//        }
//    }
//}
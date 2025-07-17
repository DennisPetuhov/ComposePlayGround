package com.example.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.composeplayground.modifier.shimmerEffect
import com.example.composeplayground.toolBar.collapsingToolBar.CollapsingToolBar

import com.example.composeplayground.ui.theme.ComposePlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlayGroundTheme{
                Surface() {
//               Box(modifier=Modifier.fillMaxSize().shimmerEffect())
                    CollapsingToolBar()

//                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                        Column(
//                            modifier = Modifier.fillMaxSize(),
//                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
//                            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
//                        ) {
//
////
////                            Spacer(modifier = Modifier
////                                .padding(10.dp)
////                                .weight(1f))
////                    MyFocus()
////                        MyComposable()
////                        ScreenOnLaunched()
////                        NoShapeNoBorderTextField(value = "!!!!", onValueChange ={} )
////                        ExitAlwaysBottomAppBarFixedVibrant()
////                        NavigationBarSample()
//
//                            Box(
//                                modifier = Modifier
//                                    .size(80.dp)
//                                    .clip(CircleShape)
//                                    .myShimmer()
//                            )
//                            TriangleView()
////                            OctagonView(
////                                colors = listOf(
////                                    Color.Red,
////                                    Color.Blue,
////                                    Color.Green,
////                                    Color.Red,
////                                    Color.Blue,
////                                    Color.Red,
////                                    Color.Blue,
////                                    Color.Black
////                                )
////                            )
////                            AlertDialogWithIconSample()
////                            CustomModalWindowSample()
////                            AssOctaGonFun()
//
//                        }
//                    }
//                }
            }
        }
    }
}

@Composable
fun CustomModalWindowSample() {
    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { openDialog.value = true }) {
            Text("Show Custom Modal Window")
        }

        if (openDialog.value) {
            Dialog(onDismissRequest = { openDialog.value = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("This is a custom modal window.")
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = { openDialog.value = false }) {
                            Text("Close")
                        }
                    }
                }
            }
        }
    }
}}
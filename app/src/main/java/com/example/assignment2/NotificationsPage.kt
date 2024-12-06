package com.example.assignment2

import Bars.MyBottomBar
import Bars.MyTopBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun leaderboardPage(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()) {
        MyTopBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        MyBottomBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}
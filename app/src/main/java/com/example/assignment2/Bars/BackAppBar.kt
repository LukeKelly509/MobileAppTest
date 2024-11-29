package com.example.assignment2.Bars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun backAppBar(navController: NavController){
    val currentPage = navController.currentDestination?.route == "contactPage"
    val quizCategoryPage = navController.currentDestination?.route == "categoryPage"
    val tusGold = Color(0xFFA39461)
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(tusGold),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth().padding(end = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("")
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Arrow Icon",
                tint = Color.White,
                modifier = Modifier.size(35.dp).clickable {
                    navController.popBackStack()
                }
            )
        },
        actions = {
            if (currentPage) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Account Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                )
            }

            if(quizCategoryPage) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Back Arrow Icon",
                    tint = Color.White,
                    modifier = Modifier.size(35.dp).clickable {
                        navController.popBackStack()
                    }
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}
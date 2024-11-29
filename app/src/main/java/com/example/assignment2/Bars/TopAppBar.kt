package Bars

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(modifier: Modifier, navController: NavController){
    val tusGold = Color(0xFFA39461)
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(tusGold),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                contentAlignment = Alignment.Center
            ) {
//                Text("Assignment 2")
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Hamburger Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {  }
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        navController.navigate("accountDetails")
                    }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}
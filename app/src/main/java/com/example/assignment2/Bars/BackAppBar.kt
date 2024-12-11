package com.example.assignment2.Bars

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment2.Models.AccountDetailsViewModel
import com.example.assignment2.categoryPage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun backAppBar(navController: NavController){
    val contactPage = navController.currentDestination?.route == "contactPage"
    val tusGold = Color(0xFFA39461)
    val context = LocalContext.current
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
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Arrow Icon",
                tint = Color.White,
                modifier = Modifier.size(35.dp).clickable {
                    navController.popBackStack()
                }
            )
        },
        actions = {
            if (contactPage) {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Account Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            //https://github.com/LukeKelly509/TUSConnected/blob/main/app/src/main/java/com/example/tusconnected/ContactUsPage.kt - old project for this code
                            val phone = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:0873456726")
                            }
                            context.startActivity(phone)
                        }
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}
package Bars

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MyBottomBar(navController: NavController, modifier: Modifier){
    val tusGold = Color(0xFFA39461)
    BottomAppBar(
        modifier = modifier
            .background(color = Color.LightGray)
            .height(60.dp),
        containerColor = tusGold,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.navigate("homePage")
                },
                modifier = Modifier
                    .weight(1f)
                    .height(35.dp)
                    .fillMaxHeight()
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Page",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    navController.navigate("googleMaps")
                },
                modifier = Modifier
                    .weight(1f)
                    .height(35.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Google Maps",
                    tint = Color.White,
                    modifier = Modifier.size(70.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    navController.navigate("favoritesPage")
                },
                modifier = Modifier
                    .weight(1f)
                    .height(35.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = Color.White,
                    modifier = Modifier.size(70.dp)
                )
            }

            IconButton(
                onClick = {
                    navController.navigate("notificationsPage")
                },
                modifier = Modifier
                    .weight(1f)
                    .height(35.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}